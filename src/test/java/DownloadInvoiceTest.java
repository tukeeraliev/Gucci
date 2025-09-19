import com.gucci.entities.PaymentCard;
import com.gucci.entities.User;
import com.gucci.enums.Country;
import com.gucci.layers.web.page.home.HomePage;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag(Tags.SMOKE)
@Tag(Tags.WEB)
public class DownloadInvoiceTest extends BaseWebTest {

    @Test
    @DisplayName("Download Invoice after purchase order")
    @Owner("Tuke")
    @Tag("TestCase24")
    public void downloadInvoiceTest(){

        User user = User.builder()
                .title("Mr")
                .name("Tuke")                        // 🔥 обязателен для fillName()
                .email("awerqggggg@gmail.com")
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

        PaymentCard card = PaymentCard.builder()
                .nameOnCard("Tuke")
                .cardNumber("5656")
                .cvc("567")
                .expiryMonth("12")
                .expiryYear("2026")
                .build();

        open("", HomePage.class)
                .waitForPageLoaded()
                .hoverAndAddProductById("1")
                .clickViewCartBtn()
                .waitForPageLoaded()
                .clickProceedToCheckoutBtnBeforeSignup()
                .fillName(user.getName())
                .fillEmail(user.getEmail())
                .clickSignUpBtn()
                .signUpNewUser(user)
                .verifyAccountCreatedValidationMess()
                .clickContinueBtn()
                .verifyLoggedInAsUsername(user.getName())
                .clickCartBtn()
                .clickProceedToCheckoutBtnAfterSignup()
                .verifyDeliveryAddress(user)
                .verifyBillingAddress(user)
                .addComment("PLease deliver ASAP")
                .clickPlaceOrder()
                .enterPaymentDetails(card)
                .verifyCongratulationsMessageIsVisible()
                .downloadAndVerifyInvoice()
                .clickContinueBtn()
                .clickDeleteAccountBtn()
                .verifyAccountDeletedValidationMess();
    }
}
