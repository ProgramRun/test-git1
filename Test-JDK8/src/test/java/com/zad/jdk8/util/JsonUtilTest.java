package com.zad.jdk8.util;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class JsonUtilTest {

    private User u1;
    private List<User> lu = new ArrayList<>();

    @BeforeEach
    void init() {
        u1 = User.newBuilder()
                .birthday(new Date())
                .id(1)
                .username("zad")
                .build();

        User u2 = User.newBuilder()
                .birthday(new Date())
                .id(2)
                .username("zad2")
                .build();

        User u3 = User.newBuilder()
                .birthday(new Date())
                .id(3)
                .username("zad3")
                .build();

        lu.add(u1);
        lu.add(u2);
        lu.add(u3);
    }

    @Test
    void obj2String() {
        System.out.println(JsonUtil.obj2String(u1));
    }

    @Test
    void obj2StringPretty() {
        System.out.println(JsonUtil.obj2StringPretty(u1));
    }

    @Test
    void string2Obj() {
        String user = JsonUtil.obj2String(u1);
        User res = JsonUtil.string2Obj(user, User.class);
        System.out.println(res);
    }

    @Test
    void string2Obj1() {
        String s = JsonUtil.obj2StringPretty(lu);
        List<User> res = JsonUtil.string2Obj(s, new TypeReference<List<User>>() {
        });

        System.out.println(res);
    }

    @Test
    void string2Obj3() {
        String s = JsonUtil.obj2StringPretty(lu);
        List<User> res = JsonUtil.string2Obj(s, List.class, User.class);
        System.out.println(res);
    }

}

class User {
    private String username;
    private Integer id;
    private Date birthday;

    public User() {
    }

    public User(String username, Integer id, Date birthday) {
        this.username = username;
        this.id = id;
        this.birthday = birthday;
    }

    private User(Builder builder) {
        setUsername(builder.username);
        setId(builder.id);
        setBirthday(builder.birthday);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", id=" + id +
                ", birthday=" + birthday +
                '}';
    }

    public static final class Builder {
        private String username;
        private Integer id;
        private Date birthday;

        private Builder() {
        }

        public Builder username(String val) {
            username = val;
            return this;
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder birthday(Date val) {
            birthday = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}