package com.gucci.layers.web.page;

import com.codeborne.selenide.WebDriverRunner;
import com.gucci.layers.web.manager.ElementManager;
import com.gucci.layers.web.page.home.HomePage;

import static com.codeborne.selenide.Configuration.baseUrl;

public abstract class BasePage <T extends BasePage> {

    public abstract T waitForPageLoaded();
    protected final ElementManager elementManager = new ElementManager();


    public boolean isPageLoaded(String endpoint) {
        String currentUrl = WebDriverRunner.url();
        String expectedUrl = baseUrl + endpoint;
        return currentUrl.equalsIgnoreCase(expectedUrl);
    }
}
