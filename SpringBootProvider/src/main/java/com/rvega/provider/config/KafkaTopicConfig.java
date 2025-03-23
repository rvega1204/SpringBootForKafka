package com.rvega.provider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class for defining Kafka topics.
 * This class is responsible for creating and configuring Kafka topics
 * programmatically.
 */
@Configuration
public class KafkaTopicConfig {

    /**
     * Creates a Kafka topic with specific configurations.
     * The topic is configured with properties such as cleanup policy, retention
     * period,
     * segment size, and maximum message size.
     *
     * @return A NewTopic instance representing the configured Kafka topic.
     */
    @Bean
    public NewTopic generateTopic() {
        // Configuration map for the topic properties.
        Map<String, String> config = new HashMap<>();
        // Specifies the cleanup policy for the topic (e.g., delete old messages).
        config.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
        // Specifies the retention period for messages in the topic (in milliseconds).
        config.put(TopicConfig.RETENTION_MS_CONFIG, "86400000"); // 1 day
        // Specifies the maximum size of a segment file for the topic (in bytes).
        config.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824"); // 1 GB
        // Specifies the maximum size of a single message for the topic (in bytes).
        config.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012"); // ~1 MB

        // Builds and returns a new Kafka topic with the specified name, partitions,
        // replicas, and configurations.
        return TopicBuilder.name("rvg-kafka-topic")
                .partitions(2) // Number of partitions for the topic.
                .replicas(2) // Number of replicas for the topic.
                .configs(config) // Additional topic configurations.
                .build();
    }
}
