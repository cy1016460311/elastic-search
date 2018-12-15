package com.cy.demo.elasticsearch.dao;

import com.cy.demo.elasticsearch.entity.DemoUserEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表
 * 
 * @author caoyoung
 * @email cy2298842774@163.com
 * @date 2018-12-13 16:46:50
 */
@Mapper
public interface DemoUserDao extends BaseMapper<DemoUserEntity> {
	
}
