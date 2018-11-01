package com.zad.JDK8.util;

import org.junit.jupiter.api.Test;

import java.util.Date;

class JsonUtilTest {

    @Test
    void obj2String() {
    }

    @Test
    void obj2StringPretty() {
    }

    @Test
    void string2Obj() {
        User zad = User.newBuilder()
                .birthday(new Date())
                .id(111)
                .username("zad")
                .build();
        System.out.println(JsonUtil.obj2String(zad));

        String user = "{\"username\":\"zad\",\"id\":111,\"birthday\":\"2018-11-01 20:34:53\"}";
        System.out.println(JsonUtil.string2Obj(user, User.class));
    }

    @Test
    void string2Obj1() {
    }

    @Test
    void string2Obj2() {
    }

    @Test
    void obj2String1() {
    }

    @Test
    void obj2StringPretty1() {
    }

    @Test
    void string2Obj3() {
    }

    @Test
    void string2Obj4() {
    }

    @Test
    void string2Obj5() {
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