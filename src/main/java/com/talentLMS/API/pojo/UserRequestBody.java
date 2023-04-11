package com.talentLMS.API.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class UserRequestBody {
    String firstName;
    String lastName;
    String email;
    String login;
    String password;

    public UserRequestBody(User user){
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.login = user.getLogin();
        this.password = user.getPassword();
    }

}
