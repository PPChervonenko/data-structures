package com.datastructures.streamVsDatastructure;

import java.lang.reflect.Method;

class User {

    private final String email;

    User(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}


public class ExampleReflection {
    //Пожалуйста, выполните следующий метод, который возвратит электронную почту данного пользователя,
    // используя Reflection API
    public String getEmail(User user) throws Exception {
        Method getEmail = User.class.getDeclaredMethod("getEmail");
        String result = (String) getEmail.invoke(user);
    return result;
    }
    public static void main(String[] args) {

    }
}
