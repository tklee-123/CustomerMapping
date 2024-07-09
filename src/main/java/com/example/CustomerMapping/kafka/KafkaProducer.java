//package com.example.CustomerMapping.kafka;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class KafkaProducer {
//
//    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
//    private final KafkaTemplate<String, String> kafkaTemplate;
//
//    @Value("${kafka.topic.name}")
//    private String topicName;
//
//    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public void sendMessage(String message) {
//        logger.info("Sending message: {}", message);
//        kafkaTemplate.send(topicName, message);
//    }
//}
