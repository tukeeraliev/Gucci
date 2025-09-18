import com.gucci.context.CardContext;
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
}
