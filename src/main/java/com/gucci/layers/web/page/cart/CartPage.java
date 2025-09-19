package com.gucci.layers.web.page.cart;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.gucci.context.CardContext;
import com.gucci.entities.CartProduct;
import com.gucci.layers.web.page.BasePage;
import com.gucci.layers.web.page.signup_login.LoginPage;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class CartPage extends BasePage <CartPage> {

    public SelenideElement subscriptionHeader = $x("//div[@class='single-widget']/h2");
    public SelenideElement subscribeEmailInput = $("#susbscribe_email");
    public SelenideElement subscribeEmailBtn = $("#subscribe");
    public SelenideElement subscribedAlert = $x("//div[@class='alert-success alert']");
    public SelenideElement proceedToCheckoutBtn = $x("//a[text()='Proceed To Checkout']");
    public SelenideElement registerLoginBtn = $x("//u[text()='Register / Login']");

    @Step("Verify all products in cart match selected ones")
    public CartPage verifyCartMatchesContext() {
        for (CartProduct product : CardContext.getAddedProducts()) {
            SelenideElement row = $x("//tr[@id='product-" + product.getId() + "']");
            row.$(".cart_description h4 a").shouldHave(Condition.exactText(product.getName()));
            row.$(".cart_price p").shouldHave(Condition.exactText(product.getPrice()));
            row.$(".cart_quantity button").shouldHave(Condition.exactText(product.getQuantity()));
            row.$(".cart_total_price").shouldHave(Condition.exactText(product.getTotal()));
        }
        return this;
    }

    @Step("verify product's quantity in cart")
    public CartPage verifyProductQuantity(String productName, String expectedQuantity) {
        $$(".cart_info tr").findBy(text(productName)) // ищем строку с названием товара
                .$(".cart_quantity")                  // берём ячейку количества
                .shouldHave(text(expectedQuantity));  // проверяем значение
        return this;
    }

    @Step("Input email address {0} click arrow button {1}")
    public CartPage inputEmailAndClickArrow(String email) {
        elementManager.input(subscribeEmailInput, email);
        elementManager.click(subscribeEmailBtn);
        return this;
    }

    @Step("Verify success subscription message is visible")
    public CartPage verifySubscriptionSuccessMessage(SoftAssertions softly) {
        subscribedAlert.should(Condition.appear); // ждёт пока появится
        softly.assertThat(subscribedAlert.getText())
                .as("Success message")
                .contains("You have been successfully subscribed!");
        subscribedAlert.should(Condition.disappear); // ждёт пока исчезнет
        return this;
    }

    @Step("Verify subscription text is visible")
    public CartPage verifySubscriptionTextIsVisible(SoftAssertions softly) {
        softly.assertThat(subscriptionHeader.isDisplayed())
                .as("Subscription text should be visible")
                .isTrue();
        return this;
    }

    @Step("click proceed to checkout")
    public LoginPage clickProceedToCheckoutBtnBeforeSignup(){
        elementManager.click(proceedToCheckoutBtn);
        elementManager.click(registerLoginBtn);
        return Selenide.page(LoginPage.class);
    }

    @Step("click proceed to checkout")
    public CheckoutPage clickProceedToCheckoutBtnAfterSignup(){
        elementManager.click(proceedToCheckoutBtn);
        return Selenide.page(CheckoutPage.class);
    }

    @Override
    public CartPage waitForPageLoaded() {
        return this;
    }
}
