package com.fontes.project_bdnosql.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class SessionService {
    private static final long SESSION_EXPIRATION_TIME = 30;

    private final StringRedisTemplate redisTemplate;

    public SessionService(StringRedisTemplate stringRedisTemplate) {
        this.redisTemplate = stringRedisTemplate;
    }

    public void createSession(String userId, String username, String device) {
        String sessionKey = "seesion:" + userId;
        Map<String, String> sessionData = new HashMap<>();
        sessionData.put("username", username);
        sessionData.put("login_time", Instant.now().toString());
        sessionData.put("device", device);

        redisTemplate.opsForHash().putAll(sessionKey, sessionData);
        redisTemplate.expire(sessionKey, SESSION_EXPIRATION_TIME, TimeUnit.MINUTES);
    }

    public List<Map<String, String>> getAllSessions() {
        Set<String> keys = redisTemplate.keys("session*");
        List<Map<String, String>> sessions = new ArrayList<>();

        if (keys != null) {
            for(String key : keys) {
                Map<Object, Object> sessionData = redisTemplate.opsForHash().entries(key);
                Map<String, String> session = new HashMap<>();
                sessionData.forEach((k, v) -> session.put(k.toString(), v.toString()));
                sessions.add(session);
            }
        }
        return sessions;
    }

    public boolean deleteSession(String userId) {
        String sessionKey = "session:" + userId;
        Boolean deleted = redisTemplate.delete(sessionKey);
        return Boolean.TRUE.equals(deleted);
    }
}
