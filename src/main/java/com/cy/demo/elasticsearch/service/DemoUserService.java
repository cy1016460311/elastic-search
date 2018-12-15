package com.cy.demo.elasticsearch.service;

import com.baomidou.mybatisplus.service.IService;
import com.cy.demo.elasticsearch.common.utils.PageUtils;
import com.cy.demo.elasticsearch.entity.DemoUserEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * 用户表
 *
 * @author caoyoung
 * @email cy2298842774@163.com
 * @date 2018-12-13 16:46:50
 */
public interface DemoUserService extends IService<DemoUserEntity> {

    @Override
    boolean insert(DemoUserEntity userEntity);

    @Override
    boolean deleteBatchIds(Collection<? extends Serializable> ids);

    PageUtils queryPage(Integer page,Integer pageSize,String userName,String nickName);

    boolean updateById(DemoUserEntity userEntity);
}

