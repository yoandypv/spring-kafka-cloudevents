package com.sacavix.cloudeventskafkaspring.util;

import com.sacavix.cloudeventskafkaspring.entity.Order;

public class Faker {

    private static final com.github.javafaker.Faker faker = new com.github.javafaker.Faker();

    public static Order getOrder(){
        var orderId =  faker.number().numberBetween(0,100);
        var email = faker.internet().emailAddress();
        var quantity =  faker.number().numberBetween(0,6);

         return Order.builder()
                 .orderId(Long.valueOf(orderId))
                 .customerEmail(email)
                 .quantity(quantity)
                 .build();
    }
}
