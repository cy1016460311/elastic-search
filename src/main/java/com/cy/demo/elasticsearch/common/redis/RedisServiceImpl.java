package com.cy.demo.elasticsearch.common.redis;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

//@Service
public class RedisServiceImpl implements IRedisService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public Object getValue(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	public void setValue(String key, Object obj) {
		redisTemplate.opsForValue().set(key, obj);
	}

	public Object getHashValue(String key, Object hashKey) {
		return redisTemplate.opsForHash().get(key, hashKey);
	}

	public List<Object> getHashValues(String key) {
		return redisTemplate.opsForHash().values(key);
	}

	public void putHash(String key, Object hashKey, Object value) {
		redisTemplate.opsForHash().put(key, hashKey, value);
	}

	public boolean putHashIfAbsent(String key, Object hashKey, Object value) {
		return redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
	}

	public void putHashAll(String key, Map<String, Object> m) {
		redisTemplate.opsForHash().putAll(key, m);
	}

	public Object getAndSet(String key, Object obj) {
		return redisTemplate.opsForValue().getAndSet(key, obj);
	}

	public boolean setIfAbsent(String key, Object obj) {
		return redisTemplate.opsForValue().setIfAbsent(key, obj);
	}

	public Object rightPush(String key, Object value) {
		return redisTemplate.opsForList().rightPush(key, value);
	}

	/**
	 * @param key
	 * @param start
	 * @param end
	 */
	public void range(String key, int start, int end) {
		redisTemplate.opsForList().range(key, start, end);
	}

	@Override
	public void setValue(String key, Object obj, long expireTime) {
		redisTemplate.opsForValue().set(key, obj, expireTime, TimeUnit.SECONDS);

	}

	@Override
	public void deleteValue(String key) {
		redisTemplate.delete(key);
	}

	@Override
	public List<String> scan(String regex){
		Set<String> execute = redisTemplate.execute((RedisCallback<Set<String>>) connection -> {

			Set<String> binaryKeys = new HashSet<>();

			Cursor<byte[]> cursor = connection.scan( ScanOptions.scanOptions().match(regex).count(Integer.MAX_VALUE).build());
			while (cursor.hasNext()) {
				binaryKeys.add(new String(cursor.next()));
			}
			return binaryKeys;
		});
		if(execute!=null) {
			return execute.stream().collect(Collectors.toList());
		}
		return new ArrayList<>();
	}

}
