package com.cy.demo.elasticsearch.service.impl;

import com.cy.demo.elasticsearch.common.redis.IRedisService;
import com.cy.demo.elasticsearch.common.utils.PageUtils;
import com.cy.demo.elasticsearch.repository.PersonRepository;
import com.cy.demo.elasticsearch.repository.UserRepository;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.print.Book;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.cy.demo.elasticsearch.dao.DemoUserDao;
import com.cy.demo.elasticsearch.entity.DemoUserEntity;
import com.cy.demo.elasticsearch.service.DemoUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("demoUserService")
@Transactional(rollbackFor = Exception.class)
public class DemoUserServiceImpl extends ServiceImpl<DemoUserDao, DemoUserEntity> implements DemoUserService {

    @Autowired
    DemoUserDao userDao;

    @Autowired
    UserRepository userRepositorys;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;


    @Override
    public boolean insert(DemoUserEntity userEntity) {
        userEntity.setCreateTime(new Date());
        boolean insert = userDao.insert(userEntity) > 0;
        userRepositorys.save(userEntity);
        return insert;
    }

    @Override
    public boolean deleteBatchIds(Collection<? extends Serializable> ids) {
        ids.forEach(e -> userRepositorys.deleteById(Long.valueOf(e.toString())));
        return userDao.deleteBatchIds(ids) > 0;
    }

    @Override
    public PageUtils queryPage(Integer page, Integer pageSize, String userName, String nickName) {

        // 分页参数
        Pageable pageable = new PageRequest(page, pageSize);

        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.wildcardQuery("userName", "*"+userName.toLowerCase()+"*"))
                .must(QueryBuilders.wildcardQuery("nickName", "*"+nickName.toLowerCase()+"*"));

        FieldSortBuilder createDateSort = SortBuilders.fieldSort("createTime").order(SortOrder.DESC);
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withFilter(queryBuilder).withSort(createDateSort).withPageable(pageable).build();
        Page<DemoUserEntity> search = elasticsearchTemplate.queryForPage(searchQuery,DemoUserEntity.class);

        List<DemoUserEntity> list = new ArrayList<>();
        if (search != null) {
            list = search.getContent();
        }
        PageUtils pageUtils = new PageUtils(list, search.getTotalElements(), pageSize, page);

        return pageUtils;
    }

    @Override
    public boolean updateById(DemoUserEntity userEntity) {
        boolean update = userDao.updateById(userEntity)>0;
        userRepositorys.save(userEntity);
        return update;
    }
}
