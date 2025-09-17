package com.gucci.layers.web.page.signup_login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.gucci.layers.web.page.BasePage;
import com.gucci.layers.web.page.home.HomePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage extends BasePage <LoginPage> {

    public SelenideElement emailOrPasswordIncorrectTextHeader=$x("//p[text()='Your email or password is incorrect!']");
    public SelenideElement emailAddressAlreadyExistTextHeader=$x("//p[text()='Email Address already exist!']");
    public SelenideElement signUpHeaderIsVisible=$x("//h2[text()='New User Signup!']");
    public SelenideElement loginToYourAccountHeader=$x("//h2[text()='Login to your account']");
    public SelenideElement signUpName=$x("//input[@name='name']");
    public SelenideElement signUpEmail=$x("//input[@data-qa='signup-email']");
    public SelenideElement signUpBtn =$x("//button[@data-qa='signup-button']");
    public SelenideElement loginEmailInput=$x("//input[@data-qa='login-email']");
    public SelenideElement loginPasswordInput=$x("//input[@data-qa='login-password']");
    public SelenideElement loginBtn =$x("//button[@data-qa='login-button']");
    public SelenideElement incorrectEmailOrPasswordError=$x("//input[@placeholder='Password']/following-sibling::p[text()='Your email or password is incorrect!']");
    public SelenideElement emailAlreadyExistError=$x("//p[normalize-space(text())='Email Address already exist!']");
    public SelenideElement homePageBtn=$x("//a[normalize-space(text())='Home']");

    @Override
    public LoginPage waitForPageLoaded() {
        loginToYourAccountHeader.shouldHave(Condition.exactText("Login to your account"));
        return Selenide.page(LoginPage.class);
    }


    @Step("input user name {0}")
    public LoginPage fillName(String name){
        elementManager.input(signUpName, name);
        return this;
    }

    @Step("input user email {0}")
    public LoginPage fillEmail(String email){
        elementManager.input(signUpEmail, email);
        return this;
    }

    @Step("click sign up button {0}")
    public SignUpPage clickSignUpBtn(){
        elementManager.click(signUpBtn);
        return page(SignUpPage.class);
    }

    @Step("Click signup button {0}")
    public <T> T clickSignUpBtn(Class<T> pageClass){
        elementManager.click(signUpBtn);
        return page(pageClass);
    }

    @Step("Fill email {0} and password {1}")
    public <T> T fillLoginInput(String email, String password, Class<T> pageClass){
        elementManager
                .input(loginEmailInput, email)
                .input(loginPasswordInput, password)
                .click(loginBtn);
        return page(pageClass);
    }

    @Step("Verify error your email or password is incorrect {0}")
    public LoginPage verifyLoginIncorrectValidationMess(){
        emailOrPasswordIncorrectTextHeader.shouldHave(Condition.exactText("Your email or password is incorrect!"));
        return this;
    }

    @Step("Verify message email already exist {0}")
    public LoginPage verifyExistingEmailValidationMess(){
        emailAddressAlreadyExistTextHeader.shouldHave(Condition.exactText("Email Address already exist!"));
        return this;
    }
}
