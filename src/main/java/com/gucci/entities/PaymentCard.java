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
public class PaymentCard {

    String nameOnCard;
    String cardNumber;
    String cvc;
    String expiryMonth;
    String expiryYear;
}
