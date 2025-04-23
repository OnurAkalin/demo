package com.example.demo.services.kafka;

import com.example.demo.constants.KafkaConstants;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @KafkaListener(topics = KafkaConstants.TOPIC, groupId = "my-group")
    public void listen(String message) {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignored) {
        }

        System.out.println("Received message: " + message);
    }
}
