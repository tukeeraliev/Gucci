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
public class Product {

    private String name;
    private String category;
    private String price;
    private String availability;
    private String condition;
    private String brand;
}
