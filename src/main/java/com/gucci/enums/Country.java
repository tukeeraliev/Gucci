package com.gucci.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter

public enum Country {
    UNITED_STATES("United States"),
    INDIA("India"), CANADA("Canada"), AUSTRALIA("Australia"),
    ISRAEL("Israel"), NEW_ZEALAND("New Zealand"), SINGAPORE("Singapore");


    private final String country;

    Country(String country) {
        this.country = country;
    }
}
