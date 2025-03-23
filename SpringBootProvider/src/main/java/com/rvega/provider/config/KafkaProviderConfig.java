package com.rvega.provider.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class for setting up Kafka producer properties and beans.
 * This class defines the necessary configurations for connecting to a Kafka
 * broker,
 * creating a producer factory, and setting up a Kafka template for sending
 * messages.
 */
@Configuration
public class KafkaProviderConfig {

    /**
     * The Kafka bootstrap servers address, injected from the application.properties
     * file.
     * This property specifies the Kafka broker(s) to connect to.
     */
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    /**
     * Creates a map of configuration properties for the Kafka producer.
     * These properties include the Kafka broker address, key serializer, and value
     * serializer.
     *
     * @return A map containing the Kafka producer configuration properties.
     */
    public Map<String, Object> producerConfig() {
        Map<String, Object> props = new HashMap<>();
        // Specifies the Kafka broker address to connect to.
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        // Specifies the serializer class for the message key.
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // Specifies the serializer class for the message value.
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return props;
    }

    /**
     * Creates a ProducerFactory bean for Kafka producers.
     * The ProducerFactory is responsible for creating Kafka producer instances
     * with the specified configuration properties.
     *
     * @return A ProducerFactory instance configured with the producer properties.
     */
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    /**
     * Creates a KafkaTemplate bean for sending messages to Kafka topics.
     * The KafkaTemplate simplifies the process of sending messages to Kafka by
     * providing high-level methods for interacting with Kafka topics.
     *
     * @param producerFactory The ProducerFactory used to create Kafka producers.
     * @return A KafkaTemplate instance configured with the producer factory.
     */
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
