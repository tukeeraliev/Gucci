package com.gucci.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartProduct {

    private String id;
    private String name;
    private String price;
    private String quantity;
    private String total;
}
