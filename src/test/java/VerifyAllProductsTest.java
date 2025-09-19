import com.gucci.entities.Product;
import com.gucci.layers.web.page.home.HomePage;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag(Tags.SMOKE)
@Tag(Tags.WEB)
public class VerifyAllProductsTest extends BaseWebTest{

    @Test
    @DisplayName("Verify All Products and product detail page")
    @Owner("Tuke")
    @Tag("TestCase8")
    public void verifyAllProductsTest(){

        Product expectedProduct = Product.builder()
                .name("Blue Top")
                .category("Category: Women > Tops")
                .price("Rs. 500")
                .availability("Availability: In Stock")
                .condition("Condition: New")
                .brand("Brand: Polo")
                .build();

        open("", HomePage.class)
                .waitForPageLoaded()
                .clickProductsBtn()
                .waitForPageLoaded()
                .verifyProductsListIsVisible()
                .clickViewDetailsProduct1()
                .waitForPageLoaded()
                .verifyProductDetailsAreVisible(expectedProduct);
    }

    @Test
    @DisplayName("Search Product")
    @Owner("Tuke")
    @Tag("TestCase9")
    public void searchProductTest(){
        open("", HomePage.class)
                .waitForPageLoaded()
                .clickProductsBtn()
                .waitForPageLoaded()
                .searchAndPressEnter("top")
                .waitForPageLoaded()
                .verifySearchedProductsHeaderVisible()
                .verifyAllSearchedProductsVisible()
                .hoverAndAddProductByName("Blue Top")
                .clickContinueShoppingBtn()
                .hoverAndAddProductByName("Summer White Top")
                .clickViewCartBtn()
                .verifyCartMatchesContext()
                .clickSignupLoginBth()
                .fillLoginInput("uuii@gmail.com", "1234", HomePage.class)
                .clickCartBtn()
                .verifyCartMatchesContext();
    }
}
