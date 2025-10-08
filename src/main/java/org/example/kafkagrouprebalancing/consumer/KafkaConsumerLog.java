package org.example.kafkagrouprebalancing.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerLog {

    private final static Logger logger = LoggerFactory.getLogger(KafkaConsumerLog.class);

    @KafkaListener(topics = "deneme-topic", groupId = "log-group", containerFactory = "factoryMember1")
    public void listen1(String msg) {
        logger.info("(log-group) Member1 got: " + msg);
    }

    @KafkaListener(topics = "deneme-topic", groupId = "log-group", containerFactory = "factoryMember2")
    public void listen2(String msg) {
        logger.info("(log-group) Member2 got: " + msg);
    }

    @KafkaListener(topics = "deneme-topic", groupId = "log-group", containerFactory = "factoryMember3")
    public void listen3(String msg) {
        logger.info("(log-group)Member3 got: " + msg);
    }

    @KafkaListener(topics = "deneme-topic", groupId = "log-group", containerFactory = "factoryMember4")
    public void listen4(String msg) {
        logger.info("(log-group) Member4 got: " + msg);
    }

    @KafkaListener(topics = "deneme-topic", groupId = "log-group", containerFactory = "factoryMember5")
    public void listen5(String msg) {
        logger.info("(log-group) Member5 got: " + msg);
    }
}
