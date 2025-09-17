package com.gucci.layers.web.page.signup_login;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.gucci.entities.User;
import com.gucci.enums.Country;
import com.gucci.layers.web.page.BasePage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SignUpPage extends BasePage<SignUpPage> {

    public SelenideElement enterAccountInformationHeader = $x("//b[text()='Enter Account Information']");
    public SelenideElement password = $("#password");
    public SelenideElement selectDays = $("#days");
    public SelenideElement selectMonths = $("#months");
    public SelenideElement selectYears = $("#years");
    public SelenideElement checkboxNewsLetter = $("#newsletter");
    public SelenideElement checkboxOption = $("#optin");
    public SelenideElement firstNameInput = $("#first_name");
    public SelenideElement lastNameInput = $("#last_name");
    public SelenideElement companyInput = $("#company");
    public SelenideElement address1Input = $("#address1");
    public SelenideElement address2Input = $("#address2");
    public SelenideElement countrySelect = $("#country");
    public SelenideElement state = $("#state");
    public SelenideElement city = $("#city");
    public SelenideElement zipcode = $("#zipcode");
    public SelenideElement mobileNumber = $("#mobile_number");
    public SelenideElement createAccountButton = $x("//button[@data-qa='create-account']");
    public SelenideElement name = $(By.id("name"));
    public SelenideElement email = $(By.id("email"));



    public AccountCreatedPage signUpNewUser(User user) {
        elementManager.click($x("//input[@value='" + user.getTitle() + "']"))
                .verifyAttributeValue(name, "value", user.getName())
                .verifyAttributeValue(email, "value", user.getEmail())
                .input(password, user.getPassword());

        selectDateOfBirth(user.getDoB());

        elementManager.input(firstNameInput, user.getFirstName())
                .input(lastNameInput, user.getLastName())
                .input(companyInput, user.getCompany())
                .input(address1Input, user.getAddress())
                .input(address2Input, user.getAddress2())
                .selectByText(countrySelect, user.getCountry().getCountry())
                .input(state, user.getState())
                .input(city, user.getCity())
                .input(zipcode, user.getZipcode())
                .input(mobileNumber, user.getMobileNumber())
                .click(createAccountButton);
        return Selenide.page(AccountCreatedPage.class);
    }

    public SignUpPage selectDateOfBirth(String dateMonthYear) {

        String[] dateMonthYearParts = dateMonthYear.split("/");
        String day = dateMonthYearParts[0];
        String month = dateMonthYearParts[1];
        String year = dateMonthYearParts[2];

        elementManager.selectByValue(selectDays, day);
        elementManager.selectByValue(selectMonths, month);
        elementManager.selectByValue(selectYears, year);
        return this;
    }

    @Override
    public SignUpPage waitForPageLoaded() {
        return this;
    }
}
