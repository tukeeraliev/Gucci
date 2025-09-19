package com.gucci.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Brand {

    POLO("Polo"),
    H_M("H&M"),
    MADAME("Madame"),
    MAST_HARBOUR("Mast & Harbour"),
    BABYHUG("Babyhug"),
    ALLEN_SOLLY_JUNIOR("Allen Solly Junior"),
    KOOKIE_KIDS("Kookie Kids"),
    BIBA("Biba");

    private final String name;
}
