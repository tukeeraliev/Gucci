package com.gucci.layers.web.page.selections;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.gucci.context.CardContext;
import com.gucci.entities.CartProduct;
import com.gucci.layers.web.page.BasePage;
import com.gucci.layers.web.page.home.HomePage;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CartPage extends BasePage <CartPage> {

    public SelenideElement subscriptionHeader = $x("//div[@class='single-widget']/h2");
    public SelenideElement subscribeEmailInput = $("#susbscribe_email");
    public SelenideElement subscribeEmailBtn = $("#subscribe");
    public SelenideElement subscribedAlert = $x("//div[@class='alert-success alert']");

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

    @Override
    public CartPage waitForPageLoaded() {
        return this;
    }
}
