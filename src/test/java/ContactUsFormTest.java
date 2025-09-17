import com.gucci.entities.User;
import com.gucci.layers.web.page.home.HomePage;
import com.gucci.steps.UserSteps;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag(Tags.SMOKE)
@Tag(Tags.WEB)
public class ContactUsFormTest extends BaseWebTest{

    @Test
    @DisplayName("Contact Us Form")
    @Owner("Tuke")
    @Tag("TestCase6")
    public void contactUsFormTest(){

        User testUser = User.builder()
                .name("Tuke")
                .email("tukeeraliev@gmail.com")
                .contactSubject("Hello Hello")
                .contactMessage("This is test message")
                .contactFilePath("src/test/resources/2025-08-11 16-39.pdf")
                .build();

        open("", HomePage.class)
                .waitForPageLoaded()
                .clickContactUsBth()
                .verifyGetInTouchValidationMess()
                .fillAndSubmitContactUsForm(testUser)
                .verifySuccessMessageIsVisible();

        new UserSteps(testUser)
                .openHomePage();
    }


}
