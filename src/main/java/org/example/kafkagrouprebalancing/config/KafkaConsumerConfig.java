package org.example.kafkagrouprebalancing.config;

import org.apache.kafka.clients.consumer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    private final String cooperativeStickyAssignor = CooperativeStickyAssignor.class.getName();
    private final String stickyAssignor = StickyAssignor.class.getName();
    private final String roundRobinAssignor = RoundRobinAssignor.class.getName();
    private final String rangeAssignor = RangeAssignor.class.getName();

    // group-id = example-group
    @Bean("exampleFactory")
    public ConcurrentKafkaListenerContainerFactory<String, String> exampleFactory(ConsumerFactory<String, String> base, ProducerFactory producerFactory) {
        return createFactory(base, roundRobinAssignor);
    }

    // group-id = log-group
    @Bean("logFactory")
    public ConcurrentKafkaListenerContainerFactory<String, String> logFactory(ConsumerFactory<String, String> base) {
        return createFactory(base, cooperativeStickyAssignor);
    }

    private ConcurrentKafkaListenerContainerFactory<String, String> createFactory(ConsumerFactory<String, String> base, String assignorClassName) {
        Map<String, Object> props = new HashMap<>(base.getConfigurationProperties());
        props.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, assignorClassName);

        var customFactory = new org.springframework.kafka.core.DefaultKafkaConsumerFactory<String, String>(props);
        var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        factory.setConsumerFactory(customFactory);
        return factory;
    }

}
