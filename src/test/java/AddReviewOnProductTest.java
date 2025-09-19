import com.gucci.layers.web.page.home.HomePage;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag(Tags.SMOKE)
@Tag(Tags.WEB)
public class AddReviewOnProductTest extends BaseWebTest{

    @Test
    @DisplayName("Add review on product")
    @Owner("Tuke")
    @Tag("TestCase21")
    public void addReviewOnProductTest(){
        open("", HomePage.class)
                .clickProductsBtn()
                .waitForPageLoaded()
                .clickViewDetailsProduct1()
                .verifyWriteReviewHeaderIsVisible()
                .fillAndSubmitReview()
                .verifyThankYouIsVisible();
    }

    @Test
    @DisplayName("Add to cart from Recommended items")
    @Owner("Tuke")
    @Tag("TestCase22")
    public void addToCartFromRecommendedTest(){
        open("", HomePage.class)
                .waitForPageLoaded()
                .verifyRecommendedListIsVisible()
                .clickAddToCartOnRecommended("Winter Top")
                .clickViewCartBtn()
                .verifyProductDisplayedInCart("Winter Top");
    }
}
