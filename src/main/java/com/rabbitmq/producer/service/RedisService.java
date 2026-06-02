package com.rabbitmq.producer.service;

import com.rabbitmq.producer.Entites.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

@Service
public class RedisService {
    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    public void set(String key, Object value) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String valueAsString = objectMapper.writeValueAsString(value);
            redisTemplate.opsForValue().set(key, valueAsString);
        }catch (Exception e){
            throw new RuntimeException("unable to set the data : "+e.getMessage());
        }
    }
    public Object get(String key) {
        Object value = redisTemplate.opsForValue().get(key);
        if (value == null) {
            return null;
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Company company =  objectMapper.readValue(value.toString(), Company.class);
            return company.getJoining();
        } catch (Exception e) {
            throw new RuntimeException("unable to get the data : "+e.getMessage());
        }


    }

}
