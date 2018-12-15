package com.cy.demo.elasticsearch.repository;

import com.cy.demo.elasticsearch.entity.DemoUserEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @Author: CaoYoung
 * @Description:
 * @Date: 2018-12-14 10:18
 * @Modified By:
 * @Test By
 **/
@Component
public interface UserRepository extends ElasticsearchRepository<DemoUserEntity,Long> {

}