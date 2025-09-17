package com.gucci.steps;

import com.gucci.entities.User;
import com.gucci.layers.web.page.home.HomePage;
import com.gucci.layers.web.page.signup_login.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class UserSteps {

    private User user;
    private HomePage homePage;
    private LoginPage loginPage;

    public UserSteps (User user) {
        this.user = user;
    }

    public UserSteps openHomePage() {
        homePage = open("", HomePage.class);
        homePage.waitForPageLoaded();
        return this;
    }

    public UserSteps goToLoginPage() {
        loginPage = homePage.clickSignupLoginBth();
        return this;
    }

    public UserSteps login() {
        loginPage.fillLoginInput(user.getEmail(), user.getPassword(), LoginPage.class);
        return this;
    }

    public UserSteps verifyLoginErrorMessage() {
        loginPage.verifyLoginIncorrectValidationMess();
        return this;
    }
}
