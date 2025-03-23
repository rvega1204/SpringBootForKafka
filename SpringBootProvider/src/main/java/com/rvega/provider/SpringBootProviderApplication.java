package com.rvega.provider;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Main class for the Spring Boot application.
 * This class serves as the entry point for the Spring Boot application and
 * includes
 * a CommandLineRunner bean to send an initial message to a Kafka topic.
 */
@SpringBootApplication
public class SpringBootProviderApplication {

	/**
	 * The main method that starts the Spring Boot application.
	 *
	 * @param args Command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringBootProviderApplication.class, args);
	}

	/**
	 * Initializes a CommandLineRunner bean that sends a message to a Kafka topic
	 * when the application starts.
	 *
	 * @param kafkaTemplate The KafkaTemplate used to send messages to Kafka topics.
	 * @return A CommandLineRunner instance that sends a message to the Kafka topic.
	 */
	@Bean
	CommandLineRunner init(KafkaTemplate<String, String> kafkaTemplate) {
		return args -> {
			// Sends a "Hello World" message to the specified Kafka topic.
			kafkaTemplate.send("rvg-kafka-topic", "Hello World from Kafka with Spring Boot!!");
		};
	}
}
