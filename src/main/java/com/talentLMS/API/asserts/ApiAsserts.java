package com.talentLMS.API.asserts;

import com.talentLMS.API.pojo.User;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;

@Slf4j
public class ApiAsserts {
    private Response response;
    private ResponseBody responseBody;
    User user;

    public ApiAsserts(Response response) {
        this.response = response;
    }

    public static ApiAsserts assertsThatResponse(Response response) {
        log.info("Start assert response...");
        return new ApiAsserts(response);
    }

    public ApiAsserts isCorrectHttpStatusCode(Integer expectedStatusCode) {
        Assertions.assertThat(this.response.getStatusCode())
                .isEqualTo(expectedStatusCode)
                .withFailMessage("Response code is incorrect, Expected {}, but found {}", expectedStatusCode, this.response.getStatusCode());
        log.info("Status code is correct: Actual {} Expected {}", this.response.getStatusCode(), expectedStatusCode);
        return this;
    }

    public void isJsonContains(String value){
        Assertions.assertThat(this.response.body().asPrettyString().contains(value)).isTrue();
        log.info("Json contains {}", value);
    }

}
