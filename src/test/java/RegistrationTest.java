import com.gucci.entities.User;
import com.gucci.enums.Country;
import com.gucci.layers.web.page.home.HomePage;
import com.gucci.utils.WaitManager;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag(Tags.SMOKE)
@Tag(Tags.WEB)
public class RegistrationTest extends BaseWebTest {

    @Test
    @DisplayName("Registration new user test")
    @Owner("Tuke")
    @Tag("TestCase1")
    public void registrationTest(){

        User user = User.builder()
                .title("Mr")
                .name("Tuke")                        // 🔥 обязателен для fillName()
                .email("azabazssaa@gmail.com")
                .password("1234")
                .DoB("12/March/2020")
                .firstName("Tuke")
                .lastName("dsds")
                .company("dsdsd")
                .address("fdfdf 56")
                .address2("fdfdf 56")
                .country(Country.INDIA)
                .state("fdfdfd")
                .city("Ankara")
                .zipcode("435454")
                .mobileNumber("07776565565")
                .build();

        var homePage = open("", HomePage.class)
                .waitForPageLoaded()
                .clickSignupLoginBth()
                .fillName(user.getName())
                .fillEmail(user.getEmail())
                .clickSignUpBtn()
                .signUpNewUser(user)
                .waitForPageLoaded()
                .verifyAccountCreatedValidationMess()
                .clickContinueBtn()
                .veryLoggedInAsUsername("Tuke")
                .clickDeleteAccountBtn()
                .verifyAccountDeletedValidationMess()
                .clickContinueBtn();



        WaitManager.pauseInSeconds(5);
    }
}
