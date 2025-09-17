import com.gucci.entities.User;
import com.gucci.enums.Country;
import com.gucci.layers.web.page.home.HomePage;
import com.gucci.layers.web.page.signup_login.LoginPage;
import com.gucci.steps.UserSteps;
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

        open("", HomePage.class)
                .waitForPageLoaded()
                .clickSignupLoginBth()
                .fillName(user.getName())
                .fillEmail(user.getEmail())
                .clickSignUpBtn()
                .signUpNewUser(user)
                .waitForPageLoaded()
                .verifyAccountCreatedValidationMess()
                .clickContinueBtn()
                .verifyLoggedInAsUsername("Tuke")
                .clickDeleteAccountBtn()
                .verifyAccountDeletedValidationMess()
                .clickContinueBtn();



        WaitManager.pauseInSeconds(5);
    }

    @Test
    @DisplayName(" Login User with correct email and password")
    @Owner("Tuke")
    @Tag("TestCase2")
    public void loginWithCorrectDataTest(){
        open("", HomePage.class)
                .waitForPageLoaded()
                .clickSignupLoginBth()
                .fillLoginInput("azazaz@mail.ru", "1234", HomePage.class)
                .verifyLoggedInAsUsername("Tuke")
                .clickDeleteAccountBtn()
                .verifyAccountDeletedValidationMess()
                .clickContinueBtn();


    }

    @Test
    @DisplayName("Login User with incorrect email and password")
    @Owner("Tuke")
    @Tag("TestCase3")
    public void LoginWithIncorrectDataTest(){

        User testUser = User.builder()
                .email("azazajjjjjz@mail.ru")
                .password("1234")
                .build();

        new UserSteps(testUser)
                .openHomePage()
                .goToLoginPage()
                .login()
                .verifyLoginErrorMessage();


    }

    @Test
    @DisplayName("Logout User")
    @Owner("Tuke")
    @Tag("TestCase4")
    public void LogoutUserTest(){
        open("", HomePage.class)
                .waitForPageLoaded()
                .clickSignupLoginBth()
                .verifyLoginPageHeaderIsVisible()
                .fillLoginInput("azazaz@mail.ru", "1234", HomePage.class)
                .verifyLoggedInAsUsername("Tuke")
                .clickLogoutBtn()
                .verifyLoginPageHeaderIsVisible();


    }

    @Test
    @DisplayName("Register User with existing email")
    @Owner("Tuke")
    @Tag("TestCase5")
    public void registerWithExistingEmail(){
        open("", HomePage.class)
                .waitForPageLoaded()
                .clickSignupLoginBth()
                .verifyLoginPageHeaderIsVisible()
                .fillName("Tuke")
                .fillEmail("tukeeraliev@gmail.com")
                .clickSignUpBtn(LoginPage.class)
                .verifyExistingEmailValidationMess();
    }
}
