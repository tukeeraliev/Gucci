import com.gucci.layers.web.page.home.HomePage;
import io.qameta.allure.Owner;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag(Tags.SMOKE)
@Tag(Tags.WEB)
public class VerifyScrollUpUsingArrowTest extends BaseWebTest {

    @Test
    @DisplayName("Verify Scroll Up using 'Arrow' button and Scroll Down functionality")
    @Owner("Tuke")
    @Tag("TestCase25")
    public void verifyScrollUpUsingArrowTest() {

        SoftAssertions softly = new SoftAssertions();

        open("", HomePage.class)
                .waitForPageLoaded()
                .scrollToFooter()
                .verifySubscriptionTextIsVisible(softly)
                .scrollToTopAndVerifyAutomationHeaderIsVisible();
    }

    @Test
    @DisplayName("Verify Scroll Up without 'Arrow' button and Scroll Down functionality")
    @Owner("Tuke")
    @Tag("TestCase26")
    public void verifyScrollUpWithoutArrowTest() {

        SoftAssertions softly = new SoftAssertions();

        open("", HomePage.class)
                .waitForPageLoaded()
                .scrollToFooter()
                .verifySubscriptionTextIsVisible(softly)
                .scrollToTopWithoutArrowAndVerifyHeader();
    }
}
