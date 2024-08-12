package com.example.demo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "test")
public class SolrVo {

    @Id
    @Indexed(name = "id")
    private String id;

    @Indexed(name = "name")
    private String name;

    @Indexed(name = "age")
    private String age;
}
