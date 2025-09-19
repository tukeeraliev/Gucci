package com.gucci.layers.web.page.cart;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.gucci.entities.PaymentCard;
import com.gucci.layers.web.page.BasePage;
import com.gucci.layers.web.page.signup_login.DeletedAccountPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class PaymentPage extends BasePage<PaymentPage> {

    public SelenideElement nameOnCardInput = $("input[name='name_on_card']");
    public SelenideElement cardNumberInput = $("input[name='card_number']");
    public SelenideElement cvcInput        = $("input[name='cvc']");
    public SelenideElement expiryMonthInput= $("input[name='expiry_month']");
    public SelenideElement expiryYearInput = $("input[name='expiry_year']");
    public SelenideElement payBtn          = $(byText("Pay and Confirm Order"));
    public SelenideElement congratulationsMessageHeader = $x("//p[text()='Congratulations! Your order has been confirmed!']");
    public SelenideElement deleteAccountBtn = $x("//a[@href='/delete_account']");

    @Step("Click delete account button")
    public DeletedAccountPage clickDeleteAccountBtn(){
        elementManager.click(deleteAccountBtn);
        return Selenide.page(DeletedAccountPage.class);
    }

    @Step("Verify congratulations message is visible")
    public PaymentPage verifyCongratulationsMessageIsVisible(){
        congratulationsMessageHeader.shouldHave(Condition.exactText("Congratulations! Your order has been confirmed!"));
        return this;
    }

    @Step("Вводим данные карты: {0}, {1}, {2}, {3}/{4}")
    public PaymentPage enterPaymentDetails(PaymentCard card){
        elementManager.input(nameOnCardInput, "Tuke")
                .input(cardNumberInput, "123456788765432")
                .input(cvcInput, "123")
                .input(expiryMonthInput, "March")
                .input(expiryYearInput, "2026")
                .click(payBtn);
        return this;
    }

    @Override
    public PaymentPage waitForPageLoaded() {
        return this;
    }
}
