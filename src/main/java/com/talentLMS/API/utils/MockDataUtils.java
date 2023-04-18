package com.talentLMS.API.utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class MockDataUtils {

    private static final Faker faker = new Faker(new java.util.Locale("en"));
    private MockDataUtils(){

    }

    public static String generateFirsNAme(){
        return faker.name().firstName();
    }

    public static String generateLastName(){
        return faker.name().lastName();
    }

    public static String generateEmail(){
        return faker.internet().emailAddress();
    }

    public static String generateLogin(){
        return faker.name().username();
    }

    public static String generatePassword(){
        return faker.internet()
                .password(10, 20, true, true, true);
    }

    public static String generateCourseName(){
        return faker.educator().course();
    }

    public static String generateDescription(){
        return faker.lorem().sentence();
    }

    public static String generateCodeNumber(){
        int num =  faker.number().numberBetween(1,101);
        return String.format("%03d", num);
    }
}
