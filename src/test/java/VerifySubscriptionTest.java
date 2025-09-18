import com.gucci.layers.web.page.home.HomePage;
import io.qameta.allure.Owner;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag(Tags.SMOKE)
@Tag(Tags.WEB)
public class VerifySubscriptionTest extends BaseWebTest{

    @Test
    @DisplayName("Registration new user test")
    @Owner("Tuke")
    @Tag("TestCase10")
    public void verifySubscriptionHomePageTest(){

        SoftAssertions softly = new SoftAssertions();

        open("", HomePage.class)
                .waitForPageLoaded()
                .scrollToFooter()
                .verifySubscriptionTextIsVisible(softly)
                .inputEmailAndClickArrow("tukeeraliev@gmail.com")
                .verifySubscriptionSuccessMessage(softly);

        softly.assertAll();
    }

    @Test
    @DisplayName("Registration new user test")
    @Owner("Tuke")
    @Tag("TestCase11")
    public void verifySubscriptionCartPageTest(){

        SoftAssertions softly = new SoftAssertions();

        open("", HomePage.class)
                .clickCartBtn()
                .waitForPageLoaded()
                .verifySubscriptionTextIsVisible(softly)
                .inputEmailAndClickArrow("tukeeraliev@gmail.com")
                .verifySubscriptionSuccessMessage(softly);

        softly.assertAll();
    }
}
