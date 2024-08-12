package com.example.demo.Controller;

import com.example.demo.config.KafkaProducer;
import com.example.demo.domain.SearchRequest;
import com.example.demo.service.ResultConsumer;
import com.example.demo.service.SolrService;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/solr")
public class Controller {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private SolrService solrService;

    @Autowired
    private ResultConsumer resultConsumer;

    // Apache Kafka(Que) 적용 실행 = solr insert
    @PostMapping("/send")
    public ResponseEntity<?> send(@RequestBody String message){
        kafkaProducer.sendMessage(message);
        System.out.println("Kafka로 메시지 전송 완료");
        return ResponseEntity.ok("Kafka로 메시지 전송됨");
    }
}
