package com.sacavix.cloudeventskafkaspring.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class Order {
    private Long orderId;
    private String customerEmail;
    private Integer quantity;
}
