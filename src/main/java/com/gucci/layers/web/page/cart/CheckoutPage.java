package com.gucci.layers.web.page.cart;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.gucci.layers.web.page.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CheckoutPage extends BasePage<CheckoutPage>{

    // Локаторы
    public SelenideElement deliveryAddressBlock = $x("//h3[text()='Your delivery address']");
    public SelenideElement billingAddressBlock  = $x("//h3[text()='Your billing address']");
    public SelenideElement commentTextArea      = $("textarea[name='message']");
    public SelenideElement placeOrderBtn        = $(byText("Place Order"));
    public SelenideElement addressDetailsHeader = $x("//h2[text='Address Details']");


    @Step("Проверяем Delivery Address содержит {0}")
    public CheckoutPage verifyDeliveryAddressIsVisible() {
        deliveryAddressBlock.shouldHave(Condition.exactText("Your delivery address"));
        return this;
    }

    @Step("Проверяем Billing Address содержит {0}")
    public CheckoutPage verifyBillingAddressIsVisible() {
        billingAddressBlock.shouldHave(Condition.exactText("Your billing address"));
        return this;
    }

    @Step("Добавляем комментарий к заказу: {0}")
    public CheckoutPage addComment(String comment) {
        elementManager.input(commentTextArea, comment);
        return this;
    }

    @Step("Нажимаем кнопку Place Order")
    public PaymentPage clickPlaceOrder() {
        elementManager.click(placeOrderBtn);
        return page(PaymentPage.class);
    }

    @Override
    public CheckoutPage waitForPageLoaded() {
        addressDetailsHeader.shouldBe(visible);
        return this;
    }
}
