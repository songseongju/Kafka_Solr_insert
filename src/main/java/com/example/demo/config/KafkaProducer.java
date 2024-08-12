package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.net.URLDecoder;

@Service
public class KafkaProducer {

    private static final String TOPIC = "test_topic";

    private static final String QUERY_TOPIC = "search_query_topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendQuery(String query) {
        try {
            String decodedQuery = URLDecoder.decode(query, StandardCharsets.UTF_8.toString());
            kafkaTemplate.send(QUERY_TOPIC, decodedQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
    }
}