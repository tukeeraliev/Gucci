import com.gucci.layers.web.page.home.HomePage;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag(Tags.SMOKE)
@Tag(Tags.WEB)
public class VerifyTestCasesTest extends BaseWebTest{

    @Test
    @DisplayName("Verify Test Cases Page")
    @Owner("Tuke")
    @Tag("TestCase7")
    public void verifyTestCasesTest(){
        open("", HomePage.class)
                .waitForPageLoaded()
                .clickTestCasesBtn()
                .verifyTestCasesValidationMess();
    }
}
