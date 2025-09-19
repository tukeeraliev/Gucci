package com.gucci.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Category {

    WOMEN_TOPS("Women", "Tops", "WOMEN - TOPS PRODUCTS"),
    WOMEN_DRESS("Women", "Dress", "WOMEN - DRESSES PRODUCTS"),
    WOMEN_SAREE("Women", "Saree", "WOMEN - SAREE PRODUCTS"),

    MEN_TSHIRTS("Men", "Tshirts", "MEN - TSHIRTS PRODUCTS"),
    MEN_JEANS("Men", "Jeans", "MEN - JEANS PRODUCTS"),

    KIDS_DRESS("Kids", "Dress", "KIDS - DRESS PRODUCTS"),
    KIDS_TOPS_SHIRTS("Kids", "Tops & Shirts", "KIDS - TOPS & SHIRTS PRODUCTS");

    public String category;
    public String subCategory;
    public String expectedHeader;
}
