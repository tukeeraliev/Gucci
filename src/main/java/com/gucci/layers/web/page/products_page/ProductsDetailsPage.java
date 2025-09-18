package com.gucci.layers.web.page.products_page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.gucci.entities.Product;
import com.gucci.layers.web.page.BasePage;

import static com.codeborne.selenide.Selenide.$x;

public class ProductsDetailsPage extends BasePage<ProductsDetailsPage> {

    public SelenideElement productName = $x("//div[@class='product-information']//h2");
    public SelenideElement category = $x("//div[@class='product-information']//p[contains(text(),'Category')]");
    public SelenideElement price = $x("//div[@class='product-information']//span/span");
    public SelenideElement availability = $x("//div[@class='product-information']//p[b[text()='Availability:']]");
    public SelenideElement condition = $x("//div[@class='product-information']//p[b[text()='Condition:']]");
    public SelenideElement brand = $x("//div[@class='product-information']//p[b[text()='Brand:']]");

    @Override
    public ProductsDetailsPage waitForPageLoaded() {
        productName.shouldBe(Condition.visible);
        return this;
    }

    public ProductsDetailsPage verifyProductDetailsAreVisible(Product product) {
        productName.shouldHave(Condition.exactText(product.getName()));
        category.shouldHave(Condition.exactText(product.getCategory()));
        price.shouldHave(Condition.exactText(product.getPrice()));
        availability.shouldHave(Condition.exactText(product.getAvailability()));
        condition.shouldHave(Condition.exactText(product.getCondition()));
        brand.shouldHave(Condition.exactText(product.getBrand()));
        return this;
    }
}
