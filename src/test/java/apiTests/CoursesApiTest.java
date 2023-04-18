package apiTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.talentLMS.API.asserts.ApiAsserts;
import com.talentLMS.API.controllers.CourseController;
import com.talentLMS.API.pojo.Courses;
import com.talentLMS.API.request.ApiRequest;
import com.talentLMS.API.utils.JsonUtils;
import com.talentLMS.API.utils.RandomEntities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.talentLMS.API.talentLmsApi.EndPoints.*;
import static java.net.HttpURLConnection.*;

public class CoursesApiTest extends BaseApiTest {
    private CourseController courseController;
    Courses course;
    List<Courses> courses;


    @BeforeClass
    public void beforeClass(){
        courseController = talentController.getCourseController();
    }
    @Test
    public void getCoursesTest(){
        courses = courseController.getCourse().jsonPath().getList("", Courses.class);
        ApiAsserts.assertsThatResponse(courseController.getResponse())
                .isCorrectHttpStatusCode(HTTP_OK)
                .isJsonContains("Advanced Features of TalentLMS");
    }

    @Test
    public void getCourseById() throws JsonProcessingException {
        courseController.getCourseBy(ID, "46");
//        course = JsonUtils.deSerializationFromJson(courseController.getResponse(), Courses.class);
        ApiAsserts.assertsThatResponse(courseController.getResponse())
                .isCorrectHttpStatusCode(HTTP_OK)
                .isJsonContains("Content and TalentLMS");
    }
    @Test
    public void gotoCourseTest(){
        courseController.gotoCourse(USER_ID, "1", COURSE_ID, "122");
        ApiAsserts.assertsThatResponse(courseController.getResponse())
                .isCorrectHttpStatusCode(HTTP_OK);
    }

    @Test
    public void createCourseTest(){
        courseController.createCourse(RandomEntities.generateCourse());
        ApiAsserts.assertsThatResponse(courseController.getResponse())
                .isCorrectHttpStatusCode(HTTP_OK);
    }

    @Test
    public void deleteCourseTest(){
        courseController.delete("126");
        ApiAsserts.assertsThatResponse(courseController.getResponse())
                .isCorrectHttpStatusCode(HTTP_OK);
    }

}
