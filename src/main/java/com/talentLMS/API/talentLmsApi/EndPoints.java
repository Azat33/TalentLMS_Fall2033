package com.talentLMS.API.talentLmsApi;

import com.talentLMS.UI.dataProviders.ConfigReader;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.Map;
@Getter
public class EndPoints {

    public static final String BRANCHES = "branches";
    public static final String CATEGORIES = "categories";
    public static final String GROUP = "group";
    public static final String COURSES = "courses";
    public static final String SYSTEM = "system";
    public static final String USERS = "users";
    public static final String SLASH = "/";
    public static final String USER_EMAIL = "email";
    public static final String ID = "id";
    public static final String USER_NAME = "username";
    public static final String IS_USER_ONLINE = "isuseronline";
    public static final String USER_ID = "user_id";
    public static final String USER_SET_STATUS = "usersetstatus";
    public static final String STATUS = "status";
    public static final String COURSE_ID = "course_id";
    public static final String FORGOT_USERNAME = "forgotusername";
    public static final String FORGOT_PASSWORD = "forgotpassword";
    public static final String GOTO_COURSE = "gotocourse";
    public static final String COURSE_COMPLETED_REDIRECT = "course_completed_redirect";
    public static final String LOGOUT_REDIRECT = "logout_redirect";
    public static final String USER_SIGNUP = "usersignup";
    public static final String DELETE_USER = "deleteuser";
    public static final String CREATE_COURSE = "createcourse";

    public static final String DELETE_COURSE = "deletecourse";


    public static Map<String, String> BASE_HEADERS = new HashMap<>() {{
        put("Authorization", ConfigReader.getProperty("apiKey"));
//        put("Content-Type", "multipart/form-data");
        put("Accept", "*/*");
        put("Accept-Encoding", "gzip, deflate, br");
        put("Connection", "keep-alive");
    }};


}
