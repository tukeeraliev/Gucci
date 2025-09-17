package com.gucci.layers.web.page.selections;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.gucci.layers.web.page.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class TestCasesPage extends BasePage <TestCasesPage> {

    public SelenideElement testCasesHeader = $x("//b[text()='Test Cases']");

    @Override
    public TestCasesPage waitForPageLoaded() {
        return null;
    }

    @Step("Test Cses text is visible")
    public TestCasesPage verifyTestCasesValidationMess(){
        testCasesHeader.shouldHave(Condition.exactText("Test Cases"));
        return this;
    }
}
