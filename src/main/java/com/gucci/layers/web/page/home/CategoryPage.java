package com.gucci.layers.web.page.home;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.gucci.layers.web.page.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class CategoryPage extends BasePage<CategoryPage> {

    // Sidebar category (Women, Men, Kids)
    private final SelenideElement categoriesSidebar = $x("//div[@id='accordian']");

    @Override
    public CategoryPage waitForPageLoaded() {
        categoriesSidebar.shouldBe(Condition.visible);
        return this;
    }

    @Step("Verify categories sidebar visible")
    public CategoryPage verifyCategoriesSidebarVisible() {
        categoriesSidebar.shouldBe(Condition.visible);
        return this;
    }

    public CategoryPage clickCategory(String category) {
        SelenideElement categoryElement = $x("//div[@class='panel-heading']//a[contains(.,'" + category + "')]");
        categoryElement.shouldBe(Condition.visible).scrollIntoView(true);
        elementManager.click(categoryElement);
        return this;
    }

    public CategoryPage clickSubCategory(String category, String subCategory) {
        SelenideElement subCategoryElement = $x("//div[@id='" + category + "']//a[normalize-space(text())='" + subCategory + "']");
        subCategoryElement.shouldBe(Condition.visible);
        elementManager.click(subCategoryElement);
        return this;
    }

    @Step("Verify category page header is {0}")
    public CategoryPage verifyCategoryPageHeader(String expectedHeader) {
        $x("//h2[@class='title text-center']").shouldHave(Condition.exactText(expectedHeader));
        return this;
    }
}
