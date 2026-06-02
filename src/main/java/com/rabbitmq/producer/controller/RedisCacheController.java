package com.rabbitmq.producer.controller;

import com.rabbitmq.producer.Entites.Company;
import com.rabbitmq.producer.service.RedisCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redisCache")
public class RedisCacheController {
    @Autowired
    private RedisCacheService redisCacheService;

    @PutMapping("/saveCompany")
     public Company saveCompanyDetails(@RequestBody Company company){
        return redisCacheService.saveCompanyDetails(company);
     }
     @GetMapping("/getCompany")
     public Company getCompanyDetails(@RequestParam  String name){
         return redisCacheService.getCompanyDetails(name);
     }
}
