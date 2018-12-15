package com.cy.demo.elasticsearch.common.redis;

import java.util.List;
import java.util.Map;

public interface IRedisService {

	public Object getValue(String key);

	public void setValue(String key, Object obj);

	public Object getHashValue(String key, Object hashKey);

	public List<Object> getHashValues(String key);

	public void putHash(String key, Object hashKey, Object value);

	public boolean putHashIfAbsent(String key, Object hashKey, Object value);

	public void putHashAll(String key, Map<String, Object> m);

	public Object getAndSet(String key, Object obj);

	public Object rightPush(String key, Object value);

	public void range(String key, int start, int end);
	
	public void setValue(String key, Object obj, long expireTime);
	
	public void deleteValue(String key);

	public List<String> scan(String regex);

}
