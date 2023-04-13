package com.talentLMS.API.utils;

import com.talentLMS.API.pojo.User;
import com.talentLMS.API.pojo.UserRequestBody;

import static com.talentLMS.API.utils.MockDataUtils.*;

public class RandomEntities {
    private RandomEntities(){

    }

    public static User generateUser(){
        return User.builder()
                .firstName(generateLastName())
                .lastName(generateLastName())
                .email(generateEmail())
                .login(generateLogin())
                .password(generatePassword())
                .build();
    }

    public static UserRequestBody generateUserBody(){
        return UserRequestBody.builder()
                .firstName(generateLastName())
                .lastName(generateLastName())
                .email(generateEmail())
                .login(generateLogin())
                .password(generatePassword())
                .build();
    }

    public static void main(String[] args) {
        System.out.println(RandomEntities.generateUserBody());
    }
}
