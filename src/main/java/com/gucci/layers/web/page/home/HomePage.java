package com.gucci.layers.web.page.home;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.gucci.layers.web.page.BasePage;
import com.gucci.layers.web.page.signup_login.DeletedAccountPage;
import com.gucci.layers.web.page.signup_login.LoginPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

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

    @Override
    public HomePage waitForPageLoaded() {
        homeOrange.shouldHave(Condition.attribute("style", "color: orange;"));
        return this;
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

    @Step("Verify that Logged in as user name is visible {0}")
    public HomePage veryLoggedInAsUsername(String user){
        loggedInAsUsernameIsVisible.shouldHave(Condition.text("Logged in as " + user));
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
}
