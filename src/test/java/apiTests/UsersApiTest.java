package apiTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.talentLMS.API.asserts.ApiAsserts;
import com.talentLMS.API.controllers.UserController;
import com.talentLMS.API.pojo.User;
import com.talentLMS.API.utils.JsonUtils;
import com.talentLMS.API.utils.RandomEntities;
import io.restassured.path.json.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static com.talentLMS.API.talentLmsApi.EndPoints.*;
import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.testng.AssertJUnit.assertEquals;
@Slf4j
public class UsersApiTest extends BaseApiTest {

    private UserController userController;
    User user;
    JsonPath jsonPath;
    List<User> users;

    @BeforeClass
    public void beforeClass() {
        userController = talentController.getUserController();
    }

    @Test
    public void receiveUsersTest() {
        users = userController.getUsers().jsonPath().getList("", User.class);
//        assertEquals("Azat", users.get(0).getFirstName());
        ApiAsserts.assertsThatResponse(userController.getResponse())
                .isCorrectHttpStatusCode(HTTP_OK)
                .isJsonContains("Azat");
    }

    @Test
    public void receiveUserByEmail() throws JsonProcessingException {
        userController.getUserBy(USER_EMAIL, "azatkarapashov@gmail.com");
//        user = JsonUtils.deSerializationFromJson(userController.getResponse(), User.class);
//        jsonPath = userController.getResponse().jsonPath();
//        assertEquals("Fiona", jsonPath.getString("first_name"));
        ApiAsserts.assertsThatResponse(userController.getResponse()).isCorrectHttpStatusCode(HTTP_OK)
                .isJsonContains("Azat");
//        assertEquals("Fiona", user.getFirstName());
    }

    @Test
    public void receiveUserById() throws JsonProcessingException {
        userController.getUserBy(ID, "22");
        user = JsonUtils.deSerializationFromJson(userController.getResponse(), User.class);
//        jsonPath = userController.getResponse().jsonPath();
//        assertEquals("Gillian", jsonPath.getString("first_name"));
        ApiAsserts.assertsThatResponse(userController.getResponse()).isCorrectHttpStatusCode(HTTP_OK)
                .isJsonContains("Gillian");
//        assertEquals("Gillian", user.getFirstName());
    }

    @Test(dependsOnMethods = "receiveUsersTest")
    public void receiveUserByUserNameTest() {
        for (User user : users) {
            userController.getUserBy(USER_NAME, user.getLogin());
            ApiAsserts.assertsThatResponse(userController.getResponse())
                    .isCorrectHttpStatusCode(HTTP_OK)
                    .isJsonContains(user.getLogin());
        }
    }

    @Test (dependsOnMethods = "receiveUsersTest")
    public void IisUserOnlineTest() {
        int online = 0;
        int offline = 0;
        for (User user : users) {
            userController.isUserOnline(user.getId());
            if (userController.getResponse().body().asString().endsWith("true}")) online++;
            else if (userController.getResponse().body().asString().endsWith("false}")) offline++;
            ApiAsserts.assertsThatResponse(userController.getResponse())
                    .isCorrectHttpStatusCode(HTTP_OK);
        }
        log.info("Count of users online: {}",  online);
        log.info("Count of users offline: {}", offline);
    }

    @Test (dependsOnMethods = "receiveUsersTest")
    public void IisUserOnlineTest2() {
        int online = 0;
        int offline = 0;
        StringBuilder onlineUsers = new StringBuilder();
        StringBuilder offlineUsers = new StringBuilder();
        for (User user : users) {
            User userOnline = userController.isUserOnline(user.getId()).as(User.class);
            if (userOnline.isOnline()) {
                onlineUsers.append(" ").append(user.getFirstName()).append(",");
                online++ ;
            }
            else {
                offlineUsers.append(" ").append(user.getFirstName()).append(",");
                offline++;
            }
            ApiAsserts.assertsThatResponse(userController.getResponse())
                    .isCorrectHttpStatusCode(HTTP_OK);
        }
        log.info("Count of users online: {} {}",  online, onlineUsers.deleteCharAt(onlineUsers.length()-1));
        log.info("Count of users offline: {} {}", offline, offlineUsers.deleteCharAt(offlineUsers.length()-1));
    }

    @Test
    public void userSetStatusTest(){
        userController.userSetStatus(USER_ID, "25", STATUS, "inactive");
        ApiAsserts.assertsThatResponse(userController.getResponse())
                .isCorrectHttpStatusCode(HTTP_OK);
    }
    @Test
    public void forgotUsernameTest(){
        userController.forgotUsername("bakr22@gmai.com");
        ApiAsserts.assertsThatResponse(userController.getResponse())
                .isCorrectHttpStatusCode(HTTP_OK);
    }
    @Test
    public void forgotPasswordTest(){
        userController.forgotPassword("norman.satterfieldcharmaine.kihn");
        ApiAsserts.assertsThatResponse(userController.getResponse())
                .isCorrectHttpStatusCode(HTTP_OK);
    }

    @Test
    public void createUserTest() throws IOException {
        userController.createUser(RandomEntities.generateUser());
        ApiAsserts.assertsThatResponse(userController.getResponse())
                .isCorrectHttpStatusCode(HTTP_CREATED);
    }
}

