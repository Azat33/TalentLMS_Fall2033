package com.talentLMS.API.request;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import static com.talentLMS.API.talentLmsApi.EndPoints.*;
import static io.restassured.RestAssured.given;
@Slf4j
@Getter
public abstract class ApiRequest {

    protected String URL;
    protected Response response;
    protected Map<String, String> headers;
    protected RequestSpecification specification;

    public ApiRequest(String url, Map<String, String> headers) {
        this.headers = headers;
        this.URL = url;
        specification = new RequestSpecBuilder()
                .addHeaders(headers)
                .setBaseUri(url)
                .build();
        specification.log();
    }


    public static String getEndpoint(String... args) {
        String endPoint = "";
        for (String arg : args) {
            endPoint = endPoint.concat(arg).concat(SLASH);
        }
        return endPoint.substring(0, endPoint.length()-1);
    }

    public void logResponse(){
        log.warn("Response is:");
        log.warn(getResponse().getBody().asString());
        log.warn(String.valueOf(getResponse().getStatusCode()));
    }

    public static String formatParameters(Map<String, String > parameters){
        StringBuilder query = new StringBuilder("?");
        for (Map.Entry<String, String> entry : parameters.entrySet()){
            query.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        return query.deleteCharAt(query.length()-1).toString();
    }

    public Response get(String endPoint){
        log.info("Perform get request: " + endPoint);
        this.response = given().spec(specification)
                .get(endPoint);
        logResponse();
        return this.response;
    }

    public Response post(String endPoint, String body){
        log.info("Preform post request: {}", endPoint);
        log.info("Body is: {}", body);
        this.response = given().spec(specification)
                .body(body)
                .post(endPoint);
        logResponse();
        return this.response;
    }

    public Response delete(String endPoint){
        log.info("Preform delete request: {}", endPoint);
        this.response = given().spec(specification)
                .delete(endPoint);
        logResponse();
        return this.response;
    }

    public Response put(String endPoint, String body){
        log.info("Preform put request: {}", endPoint);
        log.info("Body is: {}", body);
         this.response = given().spec(specification)
                .body(body)
                .put(endPoint);
         logResponse();
        return this.response;
    }
    public Response patch(String endPoint, String body){
        log.info("Preform patch request: {}", endPoint);
        log.info("Body is: {}", body);
        this.response = given().spec(specification)
                .body(body)
                .patch(endPoint);
        logResponse();
        return this.response;
    }
}
