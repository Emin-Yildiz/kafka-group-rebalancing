package org.example.kafkagrouprebalancing.controller;

import org.example.kafkagrouprebalancing.producer.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/send")
public class SendMessageController {

    private final KafkaProducer kafkaProducer;

    public SendMessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping(path = "")
    public ResponseEntity<String> sendMessageToTopic(@RequestParam(name = "message") String message) {
        return ResponseEntity.ok(kafkaProducer.sendMessageToTopic("deneme-topic",message));
    }
}
