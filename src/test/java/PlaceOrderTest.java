import com.gucci.entities.PaymentCard;
import com.gucci.entities.Product;
import com.gucci.entities.User;
import com.gucci.enums.Country;
import com.gucci.layers.web.page.home.HomePage;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag(Tags.SMOKE)
@Tag(Tags.WEB)
public class PlaceOrderTest extends BaseWebTest{

    @Test
    @DisplayName("Place Order: Register while Checkout")
    @Owner("Tuke")
    @Tag("TestCase14")
    public void placeOrderTest() {

        PaymentCard card = PaymentCard.builder()
                .nameOnCard("Tuke")
                .cardNumber("5656")
                .cvc("567")
                .expiryMonth("12")
                .expiryYear("2026")
                .build();

        User user = User.builder()
                .title("Mr")
                .name("Tuke")                        // 🔥 обязателен для fillName()
                .email("tuealievaaaa@gmail.com")
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
                .hoverAndAddProductById("1")
                .clickContinueShoppingBtn()
                .hoverAndAddProductById("2")
                .clickContinueShoppingBtn()
                .clickCartBtn()
                .waitForPageLoaded()
                .clickProceedToCheckoutBtnBeforeSignup()
                .fillName("Tuke")
                .fillEmail("tuealievaaaa@gmail.com")
                .clickSignUpBtn()
                .signUpNewUser(user)
                .waitForPageLoaded()
                .clickContinueBtn()
                .verifyLoggedInAsUsername("Tuke")
                .clickCartBtn()
                .clickProceedToCheckoutBtnAfterSignup()
                .verifyBillingAddressIsVisible()
                .verifyDeliveryAddressIsVisible()
                .addComment("Please deliver ASAP!")
                .clickPlaceOrder()
                .enterPaymentDetails(card)
                .verifyCongratulationsMessageIsVisible()
                .clickDeleteAccountBtn()
                .verifyAccountDeletedValidationMess();
    }
}
