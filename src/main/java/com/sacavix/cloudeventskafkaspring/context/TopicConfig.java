package com.sacavix.cloudeventskafkaspring.context;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {

    public static final String MAIN_TOPIC = "main-topic";

    @Bean
    public NewTopic mainTopic() {
        return TopicBuilder.
                name(MAIN_TOPIC)
                .build();
    }
}
