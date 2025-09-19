import com.gucci.enums.Brand;
import com.gucci.enums.Category;
import com.gucci.layers.web.page.home.HomePage;
import com.gucci.steps.CategoryUser;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag(Tags.SMOKE)
@Tag(Tags.WEB)
public class ViewCategoryProductsTest extends BaseWebTest{

    @Test
    @DisplayName("View Category Products")
    @Owner("Tuke")
    @Tag("TestCase18")
    public void viewCategoryProductsTest(){

        CategoryUser categoryUser = new CategoryUser();

        open("", HomePage.class);
        categoryUser.openCategories()
                .waitForPageLoaded();

        categoryUser.openSubCategory(Category.WOMEN_DRESS)
                .waitForPageLoaded();

        categoryUser.openSubCategory(Category.MEN_TSHIRTS)
                        .waitForPageLoaded();

        categoryUser.verifyCategoryPage(Category.MEN_TSHIRTS);
    }


    @Test
    @DisplayName("View & Cart Brand Products")
    @Owner("Tuke")
    @Tag("TestCase19")
    public void viewBrandProductsTest(){
        open("", HomePage.class)
                .clickProductsBtn()
                .verifyBrandTextIsVisible()
                .clickBrand(Brand.POLO)
                .waitForPageLoaded()
                .clickBrand(Brand.BIBA)
                .waitForPageLoaded();
    }
}
