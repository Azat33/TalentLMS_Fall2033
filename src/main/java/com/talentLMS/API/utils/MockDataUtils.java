package com.talentLMS.API.utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class MockDataUtils {

    private static final Faker faker = new Faker(new Locale("EN"));
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
        return faker.internet().password(10, 20, true, true, true);
    }
}
