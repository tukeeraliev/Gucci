import com.gucci.context.CardContext;
import com.gucci.entities.Product;
import com.gucci.layers.web.page.home.HomePage;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag(Tags.SMOKE)
@Tag(Tags.WEB)
public class AddProductsInCartTest extends BaseWebTest {

    @Test
    @DisplayName("Add Products in Cart")
    @Owner("Tuke")
    @Tag("TestCase12")
    public void addProductsInCartTest(){

        CardContext.clear();

        open("", HomePage.class)
                .waitForPageLoaded()
                .clickProductsBtn()
                .waitForPageLoaded()
                .hoverAndAddProductById("1")
                .clickContinueShoppingBtn()
                .hoverAndAddProductById("2")
                .clickViewCartBtn()
                .verifyCartMatchesContext();
    }

    @Test
    @DisplayName("Verify Product quantity in Cart")
    @Owner("Tuke")
    @Tag("TestCase13")
    public void verifyProductQuantityInCartText(){

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
                .clickViewProductByName()
                .verifyProductDetailsAreVisible(expectedProduct)
                .increaseProductQuantity("4")
                .addProductToCart("1", "4")
                .clickViewCartBtn()
                .verifyProductQuantity("Blue Top", "4");
    }
}
