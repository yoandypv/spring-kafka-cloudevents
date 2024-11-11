package com.sacavix.cloudeventskafkaspring.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sacavix.cloudeventskafkaspring.context.TopicConfig;
import com.sacavix.cloudeventskafkaspring.entity.Order;
import io.cloudevents.CloudEvent;
import io.cloudevents.core.CloudEventUtils;
import io.cloudevents.core.data.PojoCloudEventData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
public class OrderReceiver {

    private final ObjectMapper mapper;

    public OrderReceiver(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @KafkaListener(id = "producerListener", topics = TopicConfig.MAIN_TOPIC)
    public void listen(CloudEvent message) {

        try {
            Order order = mapper.readValue(Objects.requireNonNull(message.getData()).toBytes(), Order.class);

            if (Objects.nonNull(order)) {
                log.info("Received message. Id: {}; Data: {}", message.getId(), order);
                return;
            }

            log.warn("No data in message {}", message.getId());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
