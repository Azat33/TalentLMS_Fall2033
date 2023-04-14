package apiTests;

import com.talentLMS.API.asserts.ApiAsserts;
import com.talentLMS.API.controllers.UserController;
import com.talentLMS.API.pojo.User;
import com.talentLMS.API.utils.JsonUtils;
import com.talentLMS.API.utils.RandomEntities;
import io.restassured.path.json.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;

import static com.talentLMS.API.talentLmsApi.EndPoints.*;
import static java.net.HttpURLConnection.HTTP_OK;
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
    public void receiveUserByEmail() {
        userController.getUserBy(USER_EMAIL, "azatkarapashov@gmail.com");
//        user = JsonUtils.deSerializationFromJson(userController.getResponse(), User.class);
//        jsonPath = userController.getResponse().jsonPath();
//        assertEquals("Fiona", jsonPath.getString("first_name"));
        ApiAsserts.assertsThatResponse(userController.getResponse()).isCorrectHttpStatusCode(HTTP_OK)
                .isJsonContains("Azat");
//        assertEquals("Fiona", user.getFirstName());
    }

    @Test
    public void receiveUserById(){
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

    @Test(dependsOnMethods = "receiveUsersTest")
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

    @Test(dependsOnMethods = "receiveUsersTest")
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
        userController.userSetStatus(USER_ID, "50", STATUS, "inactive");
        ApiAsserts.assertsThatResponse(userController.getResponse())
                .isCorrectHttpStatusCode(HTTP_OK);
    }
    @Test
    public void forgotUsernameTest(){
        userController.forgotUsername("norman.satterfield@gmail.com");
        ApiAsserts.assertsThatResponse(userController.getResponse())
                .isCorrectHttpStatusCode(HTTP_OK);
    }
    @Test
    public void forgotPasswordTest(){
        userController.forgotPassword("norman.satterfieldcharmaine.kihn");
        ApiAsserts.assertsThatResponse(userController.getResponse())
                .isCorrectHttpStatusCode(HTTP_OK);
    }

    @Test(dependsOnMethods = "receiveUsersTest")
    public void createUserTest() {
        if (users.size() == 6){
            String idUser = "";
            for (int i = 0; i < users.size()-1; i++){
                if (Integer.parseInt(users.get(i).getId()) < Integer.parseInt(users.get(i+1).getId())){
                    idUser = users.get(i+1).getId();
                }
            }
            userController.deleteUser(idUser);
        }
        userController.createUser(RandomEntities.generateUser());
        ApiAsserts.assertsThatResponse(userController.getResponse())
                .isCorrectHttpStatusCode(HTTP_OK);
    }

    @Test(dependsOnMethods = "receiveUsersTest")
    public void createUserTestWithJson() {
        if (users.size() == 6){
            String idUser = "";
            for (int i = 0; i < users.size()-1; i++){
                if (Integer.parseInt(users.get(i).getId()) < Integer.parseInt(users.get(i+1).getId())){
                    idUser = users.get(i+1).getId();
                }
            }
            userController.deleteUser(idUser);
        }
        userController.createUser(RandomEntities.generateUserBody());
        ApiAsserts.assertsThatResponse(userController.getResponse())
                .isCorrectHttpStatusCode(HTTP_OK);
    }

    @Test(dependsOnMethods = "receiveUsersTest")
    public void deleteUserTest(){
        String idUser = "";
        for (int i = 0; i < users.size()-1; i++){
            if (Integer.parseInt(users.get(i).getId()) < Integer.parseInt(users.get(i+1).getId())) {
                idUser = users.get(i + 1).getId();
            }
        }
        userController.deleteUser(idUser);
        ApiAsserts.assertsThatResponse(userController.getResponse())
                .isCorrectHttpStatusCode(HTTP_OK);
    }
}

