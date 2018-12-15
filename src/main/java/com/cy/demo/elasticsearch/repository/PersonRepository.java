package com.cy.demo.elasticsearch.repository;

import com.cy.demo.elasticsearch.entity.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PersonRepository extends ElasticsearchRepository<Person, Integer> {

}
