package com.rabbitmq.producer.controller;

import com.rabbitmq.producer.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    RedisService redisService;

    @PostMapping("/set")
    public String set(@RequestBody Map<String,String> msg){
        String key=msg.get("key");
        String value=msg.get("value");
        redisService.set(key,value);
        return "Data stored successfully";
    }

    @GetMapping("/get/{key}")
    public String get(@PathVariable String key) {
        Object value = redisService.get(key);
        return value != null ? value.toString() : "Key not found";
    }

}
