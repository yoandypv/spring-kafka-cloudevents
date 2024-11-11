package com.sacavix.cloudeventskafkaspring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sacavix.cloudeventskafkaspring.context.TopicConfig;
import com.sacavix.cloudeventskafkaspring.entity.Order;
import com.sacavix.cloudeventskafkaspring.util.Faker;
import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class OrderSender {

    private final KafkaTemplate<String, CloudEvent> template;
    private final ObjectMapper mapper;

    public OrderSender (KafkaTemplate<String, CloudEvent> template,
                       ObjectMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.SECONDS)
    public void send() throws JsonProcessingException {
        Order order = Faker.getOrder();
        CloudEvent ce = CloudEventBuilder.v1()
                .withId(String.valueOf(order.getOrderId()))
                .withSource(URI.create("https://orders-api.com/orders"))
                .withType("com.sacavix.orders.OrderCreatedEvent")
                .withSubject("Order Created")
                .withTime(OffsetDateTime.now())
                .withData(mapper.writeValueAsBytes(order))
                .withExtension("email", order.getCustomerEmail())
                .build();

        template.send(TopicConfig.MAIN_TOPIC, ce)
                .thenRun(() -> log.info("Message sent. Id: {}; Data: {}", ce.getId(), order));
    }

}
