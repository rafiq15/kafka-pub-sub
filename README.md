# Kafka Pub/Sub Project

## Overview
This repository contains a sample implementation of a Kafka-based Publisher and Subscriber using Spring Boot. The project demonstrates how to publish and consume messages via Kafka, with WebSocket integration for real-time updates.

## Architecture
- **kafka-publisher**: Publishes messages to Kafka topics via REST/WebSocket endpoints.
- **kafka-subscriber**: Subscribes to Kafka topics and forwards messages to clients via WebSocket.
- **Kafka**: Managed via Docker Compose for local development.

```
[Client] <--WebSocket--> [Publisher] <--Kafka--> [Subscriber] <--WebSocket--> [Client]
```

## Prerequisites
- Java 17+
- Docker & Docker Compose
- Gradle

## Setup & Running Locally
1. **Clone the repository**
   ```powershell
   git clone <repo-url>
   cd kafka-pub-sub
   ```
2. **Start Kafka using Docker Compose**
   ```powershell
   docker-compose up -d
   ```
3. **Build and run Publisher**
   ```powershell
   cd kafka-publisher
   ./gradlew bootRun
   ```
4. **Build and run Subscriber**
   ```powershell
   cd kafka-subscriber
   ./gradlew bootRun
   ```

## Usage
- **Publish a message**: Send a POST request to the publisher's REST endpoint or use the WebSocket interface.
- **Receive messages**: Connect to the subscriber's WebSocket endpoint to receive real-time updates.

## Configuration
- Edit `src/main/resources/application.properties` in each service to configure Kafka brokers, topics, and WebSocket settings.
- Example:
  ```properties
  # Kafka broker address
  spring.kafka.bootstrap-servers=localhost:9092
  # Topic name
  kafka.topic.name=messageQueue
  ```

## Testing
- Run unit and integration tests using Gradle:
  ```powershell
  ./gradlew test
  ```

## Contribution Guidelines
- Use JavaDoc for all public classes and methods.
- Add comments to configuration files explaining each property.
- Follow standard naming conventions and keep code self-explanatory.
- Submit pull requests with a clear description of changes.

## License
This project is licensed under the MIT License.

## Troubleshooting
- Ensure Kafka is running before starting publisher/subscriber services.
- Check application logs for errors.
- For Docker issues, run `docker-compose logs`.

---
For more details, see the documentation guidelines in the repository.
