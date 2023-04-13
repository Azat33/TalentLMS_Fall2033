package com.talentLMS.API.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

public class JsonUtils {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static String serializationToJson(Object object){
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T deSerializationFromJson(Response response, Class<T> pojo) {
        String json = response.body().asString();
        try {
            return objectMapper.readValue(json, pojo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
