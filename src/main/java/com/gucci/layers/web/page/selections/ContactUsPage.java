package com.gucci.layers.web.page.selections;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.gucci.entities.User;
import com.gucci.layers.web.page.BasePage;
import io.qameta.allure.Step;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;

public class ContactUsPage extends BasePage <ContactUsPage> {

    public SelenideElement getInTouchHeader = $x("//h2[text()='Get In Touch']");
    public SelenideElement nameInput = $x("//input[@placeholder='Name']");
    public SelenideElement emailInput = $x("//input[@placeholder='Email']");
    public SelenideElement subjectInput = $x("//input[@placeholder='Subject']");
    public SelenideElement messageInput = $x("//textarea[@placeholder='Your Message Here']");
    public SelenideElement uploadFileBtn = $x("//input[@name='upload_file']");
    public SelenideElement submitBtn = $x("//input[@value='Submit']");
    private final String fileInput = "input[name='upload_file']";
    public SelenideElement successMessageHeader = $x("//div[text()='Success! Your details have been submitted successfully.']");

    @Override
    public ContactUsPage waitForPageLoaded() {
        return null;
    }

    public void uploadFile(String filePath) {
        $(fileInput).uploadFile(new File(filePath));
    }

    public ContactUsPage verifyGetInTouchValidationMess() {
        getInTouchHeader.shouldHave(Condition.exactText("Get In Touch"));
        return this;
    }

    @Step("Fill name {0}")
    public ContactUsPage fillName(String name){
        elementManager.input(nameInput, name);
        return this;
    }

    @Step("Fill email {0}")
    public ContactUsPage fillEmail(String email){
        elementManager.input(emailInput, email);
        return this;
    }

    @Step("Fill subject {0}")
    public ContactUsPage fillSubject(String subject){
        elementManager.input(subjectInput, subject);
        return this;
    }

    @Step("Fill message {0}")
    public ContactUsPage fillMessage(String message){
        elementManager.input(messageInput, message);
        return this;
    }

    @Step("Fill name {0}, Fill email {1}, Fill subject {2}, " +
            "Fill message {3}, Upload file{4}, click submit button {5}")
    public ContactUsPage fillAndSubmitContactUsForm(User user){
        fillName(user.getName())
                .fillEmail(user.getEmail())
                .fillSubject(user.getContactSubject())
                .uploadFile(user.getContactFilePath());
        elementManager.click(submitBtn);

        switchTo().alert().accept();

        return this;

    }

    public ContactUsPage verifySuccessMessageIsVisible(){
        successMessageHeader.shouldHave(Condition.exactText(
                "Success! Your details have been submitted successfully."));
        return this;
    }

}