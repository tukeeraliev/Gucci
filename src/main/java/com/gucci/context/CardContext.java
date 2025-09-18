package com.gucci.context;

import com.gucci.entities.CartProduct;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class CardContext {


    @Getter
    private static final List<CartProduct> addedProducts = new ArrayList<>();

    public static void addProduct(CartProduct product) {
        addedProducts.add(product);
    }

    public static void clear() {
        addedProducts.clear();
    }
}
