package org.example.kafkagrouprebalancing;

import org.example.kafkagrouprebalancing.producer.KafkaProducer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KafkaGroupRebalancingApplication {

    private final KafkaProducer producer;

    public KafkaGroupRebalancingApplication(KafkaProducer producer) {
        this.producer = producer;
    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaGroupRebalancingApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            var durationTime = System.currentTimeMillis();
            for (int i = 1; i <= 1000000; i++) {
                producer.sendMessageToTopic("deneme-topic","hello-message-" + i);
            }
            System.out.println("Time: " + (System.currentTimeMillis() - durationTime)/1000);
        };
    }

}
