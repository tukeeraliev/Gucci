package com.gucci.layers.web.page.home;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.gucci.context.CardContext;
import com.gucci.entities.CartProduct;
import com.gucci.layers.web.page.BasePage;
import com.gucci.layers.web.page.products.ProductsDetailsPage;
import com.gucci.layers.web.page.cart.CartPage;
import com.gucci.layers.web.page.selections.ContactUsPage;
import com.gucci.layers.web.page.products.ProductsPage;
import com.gucci.layers.web.page.selections.TestCasesPage;
import com.gucci.layers.web.page.signup_login.DeletedAccountPage;
import com.gucci.layers.web.page.signup_login.LoginPage;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class HomePage extends BasePage <HomePage> {

    public SelenideElement header = $(By.id("header"));
    public SelenideElement section = $(".features_items");
    public SelenideElement leftSideBar = $(".left-sidebar");
    public ElementsCollection brands = leftSideBar.$$x(".//div[@class='brands-name']//li");

    public SelenideElement homeOrange = $x("//a[@href='/' and contains(@style, 'orange')]");
    public SelenideElement signupLoginBtn = $x("//a[@href='/login']");
    public SelenideElement logoutBtn = $x("//i[@class='fa fa-lock']");
    public SelenideElement loggedInAsUsernameIsVisible = $x("//a[text()=' Logged in as ']");
    public SelenideElement deleteAccountBtn = $x("//a[@href='/delete_account']");
    public SelenideElement contactUsBtn = $x("//a[normalize-space(text())='Contact us']");
    public SelenideElement testCasesBtn = $x("//a[text()=' Test Cases']");
    public SelenideElement productsBtn = $x("//i[@class='material-icons card_travel']");
    public SelenideElement subscriptionHeader = $x("//div[@class='single-widget']/h2");
    public SelenideElement subscribeEmailInput = $("#susbscribe_email");
    public SelenideElement subscribeEmailBtn = $("#subscribe");
    public SelenideElement subscribedAlert = $x("//div[@class='alert-success alert']");
    public SelenideElement cart = $x("//a[@href='/view_cart']/i");
   public SelenideElement footer = $("footer");
   public SelenideElement productDetailsBtn = $x("(//a[text()='View Product'])[1]");


    @Override
    public HomePage waitForPageLoaded() {
        homeOrange.shouldHave(Condition.attribute("style", "color: orange;"));
        return this;
    }

    public ProductsDetailsPage clickViewProductByName() {
        elementManager.click(productDetailsBtn);
        return Selenide.page(ProductsDetailsPage.class);
    }

    @Step("Hover to product with id {0} and add to cart")
    public HomePage hoverAndAddProductById(String productId) {
        SelenideElement productCard = $x("//div[@class='product-image-wrapper'][.//a[@data-product-id='" + productId + "']]");

        String name = productCard.$("p").getText();
        String price = productCard.$("h2").getText();

        // по умолчанию quantity = 1, total = price
        CartProduct product = CartProduct.builder()
                .id(productId)
                .name(name)
                .price(price)
                .quantity("1")
                .total(price)
                .build();

        CardContext.addProduct(product);

        elementManager.hoverOver(productCard);
        elementManager.jsClick(productCard.$("a.add-to-cart"));

        return this;
    }

    @Step("Click continue shopping button")
    public HomePage clickContinueShoppingBtn(){
        SelenideElement continueBtn = $x("//button[text()='Continue Shopping']");
        elementManager.click(continueBtn);
        return this;
    }

    @Step("Click cart page")
    public CartPage clickCartBtn(){
        elementManager.click(cart);
        return Selenide.page(CartPage.class);
    }

    @Step("Verify success subscription message is visible")
    public HomePage verifySubscriptionSuccessMessage(SoftAssertions softly) {
        subscribedAlert.should(Condition.appear); // ждёт пока появится
        softly.assertThat(subscribedAlert.getText())
                .as("Success message")
                .contains("You have been successfully subscribed!");
        subscribedAlert.should(Condition.disappear); // ждёт пока исчезнет
        return this;
    }

    @Step("Verify subscription text is visible")
    public HomePage verifySubscriptionTextIsVisible(SoftAssertions softly) {
        softly.assertThat(subscriptionHeader.isDisplayed())
                .as("Subscription text should be visible")
                .isTrue();
        return this;
    }

    @Step("Input email address {0} click arrow button {1}")
    public HomePage inputEmailAndClickArrow(String email) {
        elementManager.input(subscribeEmailInput, email);
        elementManager.click(subscribeEmailBtn);
        return this;
    }

    @Step("Click products button")
    public ProductsPage clickProductsBtn(){
        elementManager.click(productsBtn);
        return Selenide.page(ProductsPage.class);
    }

    @Step("click sign up/login button")
    public LoginPage clickSignupLoginBth(){
        elementManager.click(signupLoginBtn);
        return Selenide.page(LoginPage.class);
    }

    public <T> T switchBetweenSection(String section, Class<T> clazz) {
        SelenideElement sectionElement = header.$(
                By.xpath(".//ul[@class='nav navbar-nav']//a[normalize-space(text())='" + section + "']"));
        elementManager.click(sectionElement);
        return Selenide.page(clazz);
    }

    public List<String> getBrands(){
        List<String> brandsList = new ArrayList<>();
        for (SelenideElement element : brands){
            brandsList.add(elementManager.getText(element));
        }
        return brandsList;
    }

    @Step("Scroll to footer")
    public HomePage scrollToFooter() {
        footer.scrollTo();
        return this;
    }

    @Step("Verify that Logged in as user name is visible {0}")
    public HomePage verifyLoggedInAsUsername(String user){
        loggedInAsUsernameIsVisible.shouldHave(text("Logged in as " + user));
        return this;
    }

    @Step("Click delete account button")
    public DeletedAccountPage clickDeleteAccountBtn(){
        elementManager.click(deleteAccountBtn);
        return Selenide.page(DeletedAccountPage.class);
    }

    @Step("Click Logout button")
    public LoginPage clickLogoutBtn(){
        elementManager.click(logoutBtn);
        return Selenide.page(LoginPage.class);
    }

    @Step("click sign up/login button")
    public ContactUsPage clickContactUsBth(){
        elementManager.click(contactUsBtn);
        return Selenide.page(ContactUsPage.class);
    }

    @Step("click Test Cases button {0}")
    public TestCasesPage clickTestCasesBtn(){
        elementManager.click(testCasesBtn);
        return Selenide.page(TestCasesPage.class);
    }
}
