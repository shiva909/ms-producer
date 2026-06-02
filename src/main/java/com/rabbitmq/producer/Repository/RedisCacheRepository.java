package com.rabbitmq.producer.Repository;

import com.rabbitmq.producer.Entites.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisCacheRepository extends CrudRepository<Company, String>  {
       Company findByName(String name);
}
