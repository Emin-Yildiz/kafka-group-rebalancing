package org.example.kafkagrouprebalancing.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    public static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "deneme-topic", groupId = "example-group", containerFactory = "exampleFactory", concurrency = "5")
    public void listen1(String msg) {
        logger.info("(example-group) Member:{} got: {}",Thread.currentThread().getName(),msg);
    }
}
