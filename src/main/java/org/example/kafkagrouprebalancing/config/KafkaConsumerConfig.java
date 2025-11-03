package org.example.kafkagrouprebalancing.config;

import org.apache.kafka.clients.consumer.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    private final String cooperativeStickyAssignor = CooperativeStickyAssignor.class.getName();
    private final String stickyAssignor = StickyAssignor.class.getName();
    private final String roundRobinAssignor = RoundRobinAssignor.class.getName();
    private final String rangeAssignor = RangeAssignor.class.getName();

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> factoryMember1(ConsumerFactory<String, String> consumerFactory) {
        return createFactory(consumerFactory, "member-1",roundRobinAssignor);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> factoryMember2(ConsumerFactory<String, String> consumerFactory) {
        return createFactory(consumerFactory, "member-2",roundRobinAssignor);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> factoryMember3(ConsumerFactory<String, String> consumerFactory) {
        return createFactory(consumerFactory, "member-3",roundRobinAssignor);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> factoryMember4(ConsumerFactory<String, String> consumerFactory) {
        return createFactory(consumerFactory, "member-4",roundRobinAssignor);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> factoryMember5(ConsumerFactory<String, String> consumerFactory) {
        return createFactory(consumerFactory, "member-5",roundRobinAssignor);
    }

    // LOG GROUP

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> logFactoryMember1(ConsumerFactory<String, String> consumerFactory) {
        return createFactory(consumerFactory, "log-member-1",cooperativeStickyAssignor);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> logFactoryMember2(ConsumerFactory<String, String> consumerFactory) {
        return createFactory(consumerFactory, "log-member-2",cooperativeStickyAssignor);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> logFactoryMember3(ConsumerFactory<String, String> consumerFactory) {
        return createFactory(consumerFactory, "log-member-3",cooperativeStickyAssignor);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> logFactoryMember4(ConsumerFactory<String, String> consumerFactory) {
        return createFactory(consumerFactory, "log-member-4",cooperativeStickyAssignor);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> logFactoryMember5(ConsumerFactory<String, String> consumerFactory) {
        return createFactory(consumerFactory, "log-member-5",cooperativeStickyAssignor);
    }

    private ConcurrentKafkaListenerContainerFactory<String, String> createFactory(ConsumerFactory<String, String> baseFactory, String groupInstanceId, String assignorClass) {

        Map<String, Object> props = new HashMap<>(baseFactory.getConfigurationProperties());
        props.put(ConsumerConfig.GROUP_INSTANCE_ID_CONFIG, groupInstanceId);
        props.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, assignorClass);

        var customFactory = new org.springframework.kafka.core.DefaultKafkaConsumerFactory<>(props);
        var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        factory.setConsumerFactory(customFactory);
        return factory;
    }

}
