package com.gucci.layers.web.page.products;

import com.codeborne.selenide.*;
import com.gucci.context.CardContext;
import com.gucci.entities.CartProduct;
import com.gucci.enums.Brand;
import com.gucci.layers.web.page.BasePage;
import com.gucci.layers.web.page.cart.CartPage;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;

public class ProductsPage extends BasePage<ProductsPage> {

    public SelenideElement productsTitle = $x("//h2[@class='title text-center' and normalize-space()='All Products']");
    public SelenideElement productsList = $(".features_items");
    public SelenideElement viewProduct1 = $x("//a[@href='/product_details/1']");
    public SelenideElement leftSideBar = $(".left-sidebar");
    public ElementsCollection brands = leftSideBar.$$x(".//div[@class='brands-name']//li");
    public SelenideElement searchInput = $("#search_product");
    public SelenideElement searchBtn = $("#submit_search");
    public SelenideElement searchedProductsHeader = $x("//div[@class='features_items']//h2[contains(normalize-space(.), 'Searched Products')]");
    public ElementsCollection searchedProducts = $$x("//div[@class='features_items']//div[contains(@class,'product-image-wrapper')]");


    @Override
    public ProductsPage waitForPageLoaded() {
        productsList.shouldBe(Condition.visible);
        return this;
    }

    @Step("Verify brands section is visible")
    public ProductsPage verifyBrandTextIsVisible() {
        SelenideElement brandsHeader = leftSideBar.$x(".//h2[contains(text(),'Brands')]");
        brandsHeader.shouldBe(Condition.visible).shouldHave(Condition.text("Brands"));
        return this;
    }

    @Step("Click brand by enum {0}")
    public ProductsPage clickBrand(Brand brand) {
        SelenideElement brandElement = brands.findBy(Condition.text(brand.getName())).$("a");
        elementManager.click(brandElement);
        return this;
    }

    @Step("Click brand by name {0}")
    public ProductsPage clickBrandByName(String brandName) {
        SelenideElement brandElement = brands.findBy(Condition.text(brandName)).$("a");
        elementManager.click(brandElement);
        return this;
    }

    @Step("Click brand by index {0}")
    public ProductsPage clickBrandByIndex(int index) {
        SelenideElement brandElement = brands.get(index).$("a");
        elementManager.click(brandElement);
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
    public ProductsDetailsPage clickViewDetailsProduct1() {
        elementManager.scrollToElement(viewProduct1);
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

    @Step("Hover and add product by name: {0}")
    public ProductsPage hoverAndAddProductByName(String productName) {
        SelenideElement productCard = $$("div.product-image-wrapper")
                .findBy(Condition.text(productName)); // ищем по названию

        productCard.hover();
        SelenideElement addToCartBtn = productCard.$("a.add-to-cart");

        elementManager.jsClick(addToCartBtn);// кликаем по кнопке внутри карточки

        // сохраним контекст (для CartPage проверки)
        CardContext.addProduct(CartProduct.builder()
                .id(productCard.$("a.add-to-cart").getAttribute("data-product-id"))
                .name(productName)
                .price(productCard.$("h2").getText().replace("Rs. ", ""))
                .quantity("1")
                .build()
        );

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
