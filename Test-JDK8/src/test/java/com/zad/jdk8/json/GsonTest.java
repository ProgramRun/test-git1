package com.zad.jdk8.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.zad.jdk8.common.Gender;
import com.zad.jdk8.common.Person;
import com.zad.jdk8.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @Author waiter
 * @Date 2020/7/1 0001 19:13
 * @Version 1.0
 * @Description
 */
@Slf4j
public class GsonTest {

    private Person p;
    private Gson gson;

    @BeforeEach
    public void beforeClass() {
        p = Person.builder().givenName("z").surName("ad").age(20).gender(Gender.MAN).build();
        TypeAdapter<String> stringAdapter = new TypeAdapter<>() {
            @Override
            public void write(JsonWriter out, String value) throws IOException {
                if (value == null) {
                    out.value("");
                    return;
                }
                out.value(value);
            }

            @Override
            public String read(JsonReader in) throws IOException {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return "";
                }
                return in.nextString();
            }
        };
        gson = new GsonBuilder().serializeNulls().registerTypeAdapterFactory(TypeAdapters.newFactory(String.class, stringAdapter)).create();
    }

    @Test
    public void testConvert() {
        log.info(gson.toJson(p));
        log.info(JsonUtil.obj2String(p));
    }
}
