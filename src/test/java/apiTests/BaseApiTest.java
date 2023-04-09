package apiTests;

import com.talentLMS.API.controllers.BaseTalentController;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

@Slf4j
public class BaseApiTest {
    protected BaseTalentController talentController = new BaseTalentController();

    @BeforeSuite
    public void beforeSuite(){
        log.warn("******************START API TEST******************");
    }
    @AfterSuite
    public void afterSuite(){
        log.warn("*******************END API TEST*******************");
    }

}
