package org.example.kafkagrouprebalancing.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerLog {

    private final static Logger logger = LoggerFactory.getLogger(KafkaConsumerLog.class);

    @KafkaListener(topics = "deneme-topic", groupId = "log-group", containerFactory = "logFactory", concurrency = "10")
    public void listen1(String msg) {
        logger.info("(log-group) Member:{} got: {}" ,Thread.currentThread().getName(),msg);
    }
}
