package com.rabbitmq.producer.service;


import com.rabbitmq.producer.Entites.Company;
import com.rabbitmq.producer.Repository.RedisCacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisCacheService {
    @Autowired
    RedisCacheRepository redisCacheRepository;
    public Company saveCompanyDetails(Company company){
       Company save =  redisCacheRepository.save(company);
       return  save;
    }

    public Company getCompanyDetails(String name){
        return redisCacheRepository.findByName(name);
    }
}
