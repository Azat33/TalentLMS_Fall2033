package com.talentLMS.API.utils;

import com.talentLMS.API.pojo.User;

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

}
