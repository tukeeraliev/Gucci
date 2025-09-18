package com.gucci.layers.web.page.products_page;

import com.codeborne.selenide.*;
import com.gucci.context.CardContext;
import com.gucci.entities.CartProduct;
import com.gucci.layers.web.page.BasePage;
import com.gucci.layers.web.page.selections.CartPage;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;

public class ProductsPage extends BasePage<ProductsPage> {

    public SelenideElement productsTitle = $x("//h2[@class='title text-center' and normalize-space()='All Products']");
    public SelenideElement productsList = $(".features_items");
    public SelenideElement viewProduct1 = $x("//a[@href='/product_details/1']");
    public SelenideElement searchInput = $("#search_product");
    public SelenideElement searchBtn = $("#submit_search");
    public SelenideElement searchedProductsHeader = $x("//div[@class='features_items']//h2[contains(normalize-space(.), 'Searched Products')]");
    public ElementsCollection searchedProducts = $$x("//div[@class='features_items']//div[contains(@class,'product-image-wrapper')]");


    @Override
    public ProductsPage waitForPageLoaded() {
        productsList.shouldBe(Condition.visible);
        return this;
    }

    @Step("Click search button {0} and press Enter")
    public ProductsPage searchAndPressEnter(String text) {
        elementManager.input(searchInput, text);
        searchBtn.sendKeys(Keys.ENTER);
        return page(ProductsPage.class);
    }

    @Step("verify Products List Is Visible {0}")
    public ProductsPage verifyProductsListIsVisible() {
        productsList.shouldBe(Condition.visible);
        return this;
    }

    @Step("Click view button of 1st product")
    public ProductsDetailsPage clickViewProduct1() {
        elementManager.click(viewProduct1);
        return Selenide.page(ProductsDetailsPage.class);
    }

    @Step("Verify 'SEARCHED PRODUCTS' is visible")
    public ProductsPage verifySearchedProductsHeaderVisible() {
        searchedProductsHeader.shouldHave(Condition.text("Searched Products"));
        return this;
    }

    @Step("Verify all searched products are visible")
    public ProductsPage verifyAllSearchedProductsVisible() {
        searchedProducts.shouldBe(CollectionCondition.sizeGreaterThan(0));
        searchedProducts.forEach(product -> product.shouldBe(Condition.visible));
        return this;
    }

    @Step("Hover to product with id {0} and add to cart")
    public ProductsPage hoverAndAddProductById(String productId) {
        SelenideElement productCard = $x("//div[@class='product-image-wrapper'][.//a[@data-product-id='" + productId + "']]");

        String name = productCard.$("p").getText();
        String price = productCard.$("h2").getText();

        // по умолчанию quantity = 1, total = price
        CartProduct product = CartProduct.builder()
                .id(productId)
                .name(name)
                .price(price)
                .quantity("1")
                .total(price)
                .build();

        CardContext.addProduct(product);

        elementManager.hoverOver(productCard);
        elementManager.jsClick(productCard.$("a.add-to-cart"));

        return this;
    }

    @Step("Click continue shopping button")
    public ProductsPage clickContinueShoppingBtn(){
        SelenideElement continueBtn = $x("//button[text()='Continue Shopping']");
        elementManager.click(continueBtn);
        return this;
    }

    @Step("Click view cart button")
    public CartPage clickViewCartBtn(){
        SelenideElement viewCart = $x("//u[text()='View Cart']");
        elementManager.click(viewCart);
        return Selenide.page(CartPage.class);
    }
}
