package com.gucci.layers.web.page.products;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.gucci.context.CardContext;
import com.gucci.entities.CartProduct;
import com.gucci.entities.Product;
import com.gucci.layers.web.page.BasePage;
import com.gucci.layers.web.page.cart.CartPage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ProductsDetailsPage extends BasePage<ProductsDetailsPage> {

    public SelenideElement productName = $x("//div[@class='product-information']//h2");
    public SelenideElement category = $x("//div[@class='product-information']//p[contains(text(),'Category')]");
    public SelenideElement price = $x("//div[@class='product-information']//span/span");
    public SelenideElement availability = $x("//div[@class='product-information']//p[b[text()='Availability:']]");
    public SelenideElement condition = $x("//div[@class='product-information']//p[b[text()='Condition:']]");
    public SelenideElement brand = $x("//div[@class='product-information']//p[b[text()='Brand:']]");
    public SelenideElement quantityInput = $("#quantity");

    @Override
    public ProductsDetailsPage waitForPageLoaded() {
        productName.shouldBe(Condition.visible);
        return this;
    }

    @Step("Increase quantity of product")
    public ProductsDetailsPage increaseProductQuantity(String quantity){
        quantityInput.clear();
        elementManager.input(quantityInput, quantity);
        return this;
    }

    @Step("Set quantity {1} for product with id {0} and add to cart")
    public ProductsDetailsPage addProductToCart(String productId, String quantity) {
        // Название товара
        String name = $(".product-information h2").getText();

        // Цена товара
        String price = $(".product-information span span").getText();

        // Устанавливаем количество
        SelenideElement qtyInput = $("#quantity");
        qtyInput.clear();
        qtyInput.setValue(quantity);

        // Добавляем товар в CartContext
        CartProduct product = CartProduct.builder()
                .id(productId)
                .name(name)
                .price(price)
                .quantity(quantity)
                .total(calculateTotal(price, quantity))
                .build();

        CardContext.addProduct(product);

        // Кликаем Add to cart
        elementManager.jsClick($(byText("Add to cart")));

        return this;
    }

    private String calculateTotal(String price, String quantity) {
        // price может быть в формате "Rs. 500"
        String onlyDigits = price.replaceAll("[^0-9]", "");
        int total = Integer.parseInt(onlyDigits) * Integer.parseInt(quantity);
        return "Rs. " + total;
    }

    @Step("Click view cart button")
    public CartPage clickViewCartBtn(){
        SelenideElement viewCart = $x("//u[text()='View Cart']");
        elementManager.click(viewCart);
        return Selenide.page(CartPage.class);
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
