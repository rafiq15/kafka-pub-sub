package publisher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class MessagePublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic;

    @Autowired
    public MessagePublisher(KafkaTemplate<String, String> kafkaTemplate, @Value("${kafka.topic}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public void publish(String message) {
        CompletableFuture<SendResult<String, String>> send = kafkaTemplate.send(topic, message);
        send.whenComplete((result, ex) -> {
            if (ex != null) {
                System.err.println("Failed to send message: " + ex.getMessage());
            } else {
                System.out.println("Message sent successfully: " + result.getProducerRecord().value()
                        + ", partition: " + result.getRecordMetadata().partition()
                        + ", offset: " + result.getRecordMetadata().offset()
                        + ", topic: " + result.getRecordMetadata().topic()
                        + ", timestamp: " + result.getRecordMetadata().timestamp()
                        + ", headers: " + result.getProducerRecord().headers()
                        + ", key: " + result.getProducerRecord().key());

            }
        });
    }
}