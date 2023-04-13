package com.talentLMS.API.controllers;

import com.talentLMS.API.request.ApiRequest;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static com.talentLMS.API.talentLmsApi.EndPoints.*;

public class CourseController extends ApiRequest {

    public CourseController(String url, Map<String, String> headers) {
        super(url, headers);
    }

    public Response getCourse(){
        return this.response = super.get(getEndpoint(COURSES));
    }
    public Response getCourseBy(String key, String value){
        Map<String, String > enter = new HashMap<>();
        enter.put(key, value);
        return this.response = super.get(getEndpoint(COURSES + formatParameters(enter)));
    }

    public Response gotoCourse(String... firstKeySecondValue){
        Map<String, String> enter = new HashMap<>();
        if (firstKeySecondValue.length % 2 != 0) throw new IllegalArgumentException("The number of arguments must be even.");
        for (int i = 0; i < firstKeySecondValue.length; i += 2){
            String key = firstKeySecondValue[i];
            String value = firstKeySecondValue[i + 1];
            enter.put(key, value);
        }
        return this.response = super.get(getEndpoint(GOTO_COURSE + formatParameters(enter)));
    }
}
