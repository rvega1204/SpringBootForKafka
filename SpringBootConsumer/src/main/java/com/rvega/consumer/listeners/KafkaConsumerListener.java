package com.rvega.consumer.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * Listener class for consuming messages from a Kafka topic.
 * This class is responsible for handling incoming messages from the specified
 * Kafka topic
 * and processing them as needed.
 */
@Configuration
public class KafkaConsumerListener {

    // Logger instance for logging received messages and other information.
    private Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerListener.class);

    /**
     * Kafka listener method that listens to messages from the specified topic.
     * The method is triggered whenever a new message is published to the topic.
     *
     * @param message The message received from the Kafka topic.
     */
    @KafkaListener(topics = { "rvg-kafka-topic" }, groupId = "rvg-kafka-id")
    public void listener(String message) {
        // Logs the received message for debugging or monitoring purposes.
        LOGGER.info("Message received, message: " + message);
    }
}
