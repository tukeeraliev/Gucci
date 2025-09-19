package com.gucci.layers.web.page.signup_login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.gucci.layers.web.page.BasePage;
import com.gucci.layers.web.page.cart.CartPage;
import com.gucci.layers.web.page.home.HomePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class AccountCreatedPage extends BasePage <AccountCreatedPage> {

    public SelenideElement accountCreatedHeader = $x("//b[text()='Account Created!']");
    public SelenideElement continueBtn = $x("//a[@class='btn btn-primary']");


    @Override
    public AccountCreatedPage waitForPageLoaded() {
        accountCreatedHeader.shouldHave(Condition.exactText("Account Created!"));
        return this;
    }

    public AccountCreatedPage verifyAccountCreatedValidationMess(){
        accountCreatedHeader.shouldHave(Condition.exactText("Account Created!"));
        return this;
    }

    public HomePage clickContinueBtn(){
        elementManager.click(continueBtn);
        return Selenide.page(HomePage.class);
    }
}
