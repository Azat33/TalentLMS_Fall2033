package com.talentLMS.API.controllers;

import com.talentLMS.API.request.ApiRequest;
import com.talentLMS.UI.dataProviders.ConfigReader;
import io.restassured.response.Response;


import java.util.HashMap;
import java.util.Map;

import static com.talentLMS.API.talentLmsApi.EndPoints.*;

public class UserController extends ApiRequest {


    public UserController(String url, Map<String, String> headers) {
        super(url, headers);
    }

    public Response getUsers() {
        return this.response = super.get(getEndpoint(API, V1, USERS));
    }
    public Response getUserBy(String key, String value){
        Map<String, String> queryParam = new HashMap<>();
        queryParam.put(key, value);
        return this.response = super.get(getEndpoint(API, V1, USERS + formatParameters(queryParam)));
    }

    public Response isUserOnline(String userId){
        Map<String, String> queryParam = new HashMap<>();
        queryParam.put(USER_ID, userId);
        return this.response = super.get(getEndpoint(API, V1, IS_USER_ONLINE + formatParameters(queryParam)));
    }
    public Response userSetStatus(String... fistKeySecondValue){
        Map<String, String> queryParam = new HashMap<>();
        if (fistKeySecondValue.length % 2 != 0)throw new IllegalArgumentException("The number of arguments must be even.");
        for (int i = 0; i < fistKeySecondValue.length; i += 2){
            String key = fistKeySecondValue[i];
            String value = fistKeySecondValue[i+1];
            queryParam.put(key, value);
        }
        return this.response = super.get(getEndpoint(API, V1,USER_SET_STATUS + formatParameters(queryParam)));
    }

    public Response forgotUsername(String userEmail){
        Map<String, String> queryParam = new HashMap<>();
        queryParam.put(USER_EMAIL, userEmail);
        return this.response = super.get(getEndpoint(API, V1, FORGOT_USERNAME + formatParameters(queryParam)));
    }

    public Response forgotPassword(String username){
        Map<String, String> queryParam = new HashMap<>();
        queryParam.put(USER_NAME, username);
        return this.response = super.get(getEndpoint(API, V1, FORGOT_PASSWORD + formatParameters(queryParam)));
    }


    public Response createUser(String requestBody) {
        return this.response = super.post(getEndpoint(API, V1, USERS), requestBody);
    }

}
