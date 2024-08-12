package com.example.demo.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ResultConsumer {

    @KafkaListener(topics = "search_result_topic", groupId = "test-group")
    public void consumeSearchResult(String message) {
        System.out.println("Received Search Result: " + message);
    }
}
