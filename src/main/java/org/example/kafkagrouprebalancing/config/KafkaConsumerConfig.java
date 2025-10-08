package org.example.kafkagrouprebalancing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> factoryMember1(ConsumerFactory<String, String> consumerFactory) {
        return createFactory(consumerFactory, "member-1");
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> factoryMember2(ConsumerFactory<String, String> consumerFactory) {
        return createFactory(consumerFactory, "member-2");
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> factoryMember3(ConsumerFactory<String, String> consumerFactory) {
        return createFactory(consumerFactory, "member-3");
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> factoryMember4(ConsumerFactory<String, String> consumerFactory) {
        return createFactory(consumerFactory, "member-4");
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> factoryMember5(ConsumerFactory<String, String> consumerFactory) {
        return createFactory(consumerFactory, "member-5");
    }

    private ConcurrentKafkaListenerContainerFactory<String, String> createFactory(
            ConsumerFactory<String, String> baseFactory,
            String groupInstanceId) {

        Map<String, Object> props = new HashMap<>(baseFactory.getConfigurationProperties());
        props.put(ConsumerConfig.GROUP_INSTANCE_ID_CONFIG, groupInstanceId);

        var customFactory = new org.springframework.kafka.core.DefaultKafkaConsumerFactory<>(props);
        var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        factory.setConsumerFactory(customFactory);
        return factory;
    }

}
