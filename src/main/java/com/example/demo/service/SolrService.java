package com.example.demo.service;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Service
public class SolrService {

    @Autowired
    private SolrClient solrClient;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.data.solr.core}")
    private String solrCore;

    private static final String RESULT_TOPIC = "search_result_topic";

    public SolrService(@Value("${spring.data.solr.host}") String solrHost) {
        this.solrClient = new HttpSolrClient.Builder(solrHost).build();
    }

    // Apache Kafka(Que) 적용 실행 = solr insert
    @KafkaListener(topics = "test_topic", groupId = "test-group")
    public void consume(String message) {
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", UUID.randomUUID().toString());
        document.addField("name", message);
        try {
            solrClient.add(solrCore, document);
            solrClient.commit(solrCore);
            System.out.println("문서 추가 성공: " + document);
        } catch (Exception e) {
            System.err.println("문서 추가 실패: " + e.getMessage());
            e.printStackTrace();
        }
    }
}