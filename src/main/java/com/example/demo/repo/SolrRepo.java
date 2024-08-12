//package com.example.demo.repo;
//
//import com.example.demo.domain.SolrVo;
//import org.springframework.data.solr.repository.Query;
//import org.springframework.data.solr.repository.SolrCrudRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface SolrRepo extends SolrCrudRepository<SolrVo, String> {
//
//    @Query("*:*")
//    List<SolrVo> search();
//
//    void save(SolrVo solrVo);
//}
