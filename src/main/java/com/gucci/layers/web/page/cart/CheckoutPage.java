package com.gucci.layers.web.page.cart;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.gucci.entities.User;
import com.gucci.layers.web.page.BasePage;
import com.gucci.layers.web.page.signup_login.DeletedAccountPage;
import io.qameta.allure.Step;

import java.io.File;
import java.io.IOException;

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
    public SelenideElement deleteAccountBtn = $("a[href='/delete_account']");

    @Step("Click 'Delete Account' button")
    public DeletedAccountPage clickDeleteAccountBtn() {
        deleteAccountBtn.click();
        return Selenide.page(DeletedAccountPage.class);
    }


    @Step("Проверяем Delivery Address совпадает с данными пользователя")
    public CheckoutPage verifyDeliveryAddress(User user) {
        SelenideElement deliveryBlock = $("#address_delivery");

        deliveryBlock.shouldBe(Condition.visible);

        // Проверка построчно, без требования убрать заголовок
        deliveryBlock.shouldHave(
                Condition.text(user.getTitle() + ". " + user.getFirstName() + " " + user.getLastName()),
                Condition.text(user.getCompany()),
                Condition.text(user.getAddress()),
                Condition.text(user.getAddress2()),
                Condition.text(user.getCity() + " " + user.getState() + " " + user.getZipcode()),
                Condition.text(user.getCountry().toString()),
                Condition.text(user.getMobileNumber())
        );

        return this;
    }

    @Step("Проверяем Billing Address совпадает с данными пользователя")
    public CheckoutPage verifyBillingAddress(User user) {
        SelenideElement billingBlock = $("#address_invoice");

        billingBlock.shouldBe(Condition.visible);

        billingBlock.shouldHave(
                Condition.text(user.getTitle() + ". " + user.getFirstName() + " " + user.getLastName()),
                Condition.text(user.getCompany()),
                Condition.text(user.getAddress()),
                Condition.text(user.getAddress2()),
                Condition.text(user.getCity() + " " + user.getState() + " " + user.getZipcode()),
                Condition.text(user.getCountry().toString()),
                Condition.text(user.getMobileNumber())
        );

        return this;
    }

    @Step("Добавляем комментарий к заказу: {0}")
    public CheckoutPage addComment(String comment) {
        elementManager.input(commentTextArea, comment);
        return this;
    }

    @Step("Нажимаем кнопку Place Order")
    public PaymentPage clickPlaceOrder() {
        elementManager.jsClick(placeOrderBtn);
        return page(PaymentPage.class);
    }

    @Override
    public CheckoutPage waitForPageLoaded() {
        addressDetailsHeader.shouldBe(visible);
        return this;
    }
}
