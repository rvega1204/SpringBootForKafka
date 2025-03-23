package com.rvega.consumer.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class for setting up Kafka consumer properties and beans.
 * This class defines the necessary configurations for connecting to a Kafka
 * broker,
 * creating a consumer factory, and setting up a Kafka listener container
 * factory.
 */
@Configuration
public class KafkaConsumerConfig {

    /**
     * The Kafka bootstrap servers address, injected from the application.properties
     * file.
     * This property specifies the Kafka broker(s) to connect to.
     */
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    /**
     * Creates a map of configuration properties for the Kafka consumer.
     * These properties include the Kafka broker address, key deserializer, and
     * value deserializer.
     *
     * @return A map containing the Kafka consumer configuration properties.
     */
    public Map<String, Object> consumerConfig() {
        Map<String, Object> props = new HashMap<>();
        // Specifies the Kafka broker address to connect to.
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        // Specifies the deserializer class for the message key.
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // Specifies the deserializer class for the message value.
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return props;
    }

    /**
     * Creates a ConsumerFactory bean for Kafka consumers.
     * The ConsumerFactory is responsible for creating Kafka consumer instances
     * with the specified configuration properties.
     *
     * @return A ConsumerFactory instance configured with the consumer properties.
     */
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    /**
     * Creates a KafkaListenerContainerFactory bean for managing Kafka listener
     * containers.
     * The KafkaListenerContainerFactory is used to create and configure listener
     * containers
     * that handle incoming Kafka messages.
     *
     * @return A KafkaListenerContainerFactory instance configured with the consumer
     *         factory.
     */
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> consumer() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        // Sets the consumer factory for the listener container factory.
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
