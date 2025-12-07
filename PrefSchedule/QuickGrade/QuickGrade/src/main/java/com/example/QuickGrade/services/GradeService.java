package com.example.QuickGrade.services;

import com.example.QuickGrade.dto.GradeMessageDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class GradeService {

    private final KafkaTemplate<String, GradeMessageDTO> kafkaTemplate;

    @Value("${app.kafka.topic}")
    private String topic;

    public GradeService(KafkaTemplate<String, GradeMessageDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(GradeMessageDTO message) {
        kafkaTemplate.send(topic, message);
        System.out.println("Sent event " + message.getStudentCode());
    }
}
