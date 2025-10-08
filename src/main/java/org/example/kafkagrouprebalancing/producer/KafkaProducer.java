package org.example.kafkagrouprebalancing.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    Logger logger = LoggerFactory.getLogger(getClass().getName());

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String sendMessageToTopic(String topicName, String message) {
        kafkaTemplate.send(topicName, message);
        return "ok";
    }

}
