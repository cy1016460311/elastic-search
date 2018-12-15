package com.cy.demo.elasticsearch.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cy.demo.elasticsearch.common.json.LongJsonDeserializer;
import com.cy.demo.elasticsearch.common.json.LongJsonSerializer;
import com.cy.demo.elasticsearch.common.json.TimestampJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * 
 * @author caoyoung
 * @email cy2298842774@163.com
 * @date 2018-12-13 16:46:50
 */
@TableName("tbl_demo_user")
@Document(indexName="user_index",type="User",indexStoreType="fs",shards=5,replicas=1,refreshInterval="-1")
public class DemoUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@Id
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long id;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 密码
	 */
	private String passWord;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 性别 1.男 2.女
	 */
	private Integer sex;
	/**
	 * 创建时间
	 */
	@JsonSerialize(using = TimestampJsonSerializer.class)
	private Date createTime;
	/**
	 * 最后一次登录时间
	 */
	@JsonSerialize(using = TimestampJsonSerializer.class)
	private Date lastLoginTime;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：用户名
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：密码
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	/**
	 * 获取：密码
	 */
	public String getPassWord() {
		return passWord;
	}
	/**
	 * 设置：昵称
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * 获取：昵称
	 */
	public String getNickName() {
		return nickName;
	}
	/**
	 * 设置：性别 1.男 2.女
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别 1.男 2.女
	 */
	public Integer getSex() {
		return sex;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：最后一次登录时间
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	/**
	 * 获取：最后一次登录时间
	 */
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
}
