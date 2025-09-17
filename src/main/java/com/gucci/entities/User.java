package com.gucci.entities;

import com.gucci.enums.Country;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    String title;
    String name;
    String password;
    String email;
    String DoB;
    String firstName;
    String lastName;
    String company;
    String address;
    String address2;
    Country country;
    String state;
    String zipcode;
    String city;
    String mobileNumber;
    String contactSubject;
    String contactMessage;
    String contactFilePath;
}
