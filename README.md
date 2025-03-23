# Spring Boot for Kafka

This project demonstrates the integration of Apache Kafka with a Spring Boot application and microservices. It provides examples of producing and consuming messages using Kafka, along with configurations.

## Features

- Kafka Producer and Consumer implementation.
- Configuration for Kafka topics, brokers, and serializers.

## Prerequisites

- Java 21 or higher.
- Apache Kafka 4.0.0 installed and running.
- Maven or Gradle for dependency management.

## Getting Started

1. Clone the repository:
    ```bash
    git clone https://github.com/rvega1204/SpringBootForKafka.git
    cd SpringBootForKafka
    ```

2. Build and run the application:
    ```bash
    ./mvnw spring-boot:run
    ```

## Project Structure

- **SpringBootProducer**: Contains classes for sending messages to Kafka topics.
- **SpringBootConsumer**: Contains classes for consuming messages from Kafka topics.

### Consuming a Message
Messages will be logged in the application console when consumed.

## License

This project is licensed under the MIT License. It's free! so you can modify it according to your needs.
