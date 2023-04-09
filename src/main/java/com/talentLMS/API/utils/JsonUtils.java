package com.talentLMS.API.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

public class JsonUtils {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static String serializationToJson(Object pojo) throws JsonProcessingException {
        return objectMapper.writeValueAsString(pojo);
    }

    public static <T> T deSerializationFromJson(Response response, Class<T> pojo) throws JsonProcessingException {
        String json = response.body().asString();
        return objectMapper.readValue(json, pojo);
    }
}
