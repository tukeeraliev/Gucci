package com.gucci.steps;

import com.gucci.enums.Category;
import com.gucci.layers.web.page.home.CategoryPage;
import com.gucci.layers.web.page.home.HomePage;
import io.qameta.allure.Step;

public class CategoryUser {

    @Step("Open Categories from sidebar")
    public CategoryPage openCategories() {
        return new HomePage()
                .waitForPageLoaded()
                .verifyCategoriesSidebarVisible();
    }

    @Step("Open {0}")
    public CategoryPage openSubCategory(Category category) {
        return new CategoryPage()
                .clickCategory(category.getCategory())
                .clickSubCategory(category.getCategory(), category.getSubCategory());
    }

    @Step("Verify category page for {0}")
    public CategoryPage verifyCategoryPage(Category category) {
        return new CategoryPage()
                .verifyCategoryPageHeader(category.getExpectedHeader());
    }
}
