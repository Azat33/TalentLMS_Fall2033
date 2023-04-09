package com.talentLMS.API.controllers;

import com.talentLMS.UI.dataProviders.ConfigReader;
import lombok.Getter;

import static com.talentLMS.API.talentLmsApi.EndPoints.BASE_HEADERS;

@Getter
public class BaseTalentController {
    private final UserController userController;
    private final CourseController courseController;


    public BaseTalentController() {
        this.userController = new UserController(ConfigReader.getProperty("url"),BASE_HEADERS);
        this.courseController = new CourseController(ConfigReader.getProperty("url"), BASE_HEADERS);
    }

}
