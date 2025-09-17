package com.gucci.layers.web.page.signup_login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.gucci.layers.web.page.BasePage;
import com.gucci.layers.web.page.home.HomePage;

import static com.codeborne.selenide.Selenide.$x;

public class DeletedAccountPage extends BasePage <DeletedAccountPage> {

    public SelenideElement accountDeletedTextHeader =$x("//b[text()='Account Deleted!']");
    public SelenideElement continueBtn = $x("//a[@class='btn btn-primary']");

    @Override
    public DeletedAccountPage waitForPageLoaded() {
        return this;
    }

    public DeletedAccountPage verifyAccountDeletedValidationMess(){
        accountDeletedTextHeader.shouldHave(Condition.exactText("Account Deleted!"));
        return this;
    }

    public HomePage clickContinueBtn(){
        elementManager.click(continueBtn);
        return Selenide.page(HomePage.class);
    }
}
