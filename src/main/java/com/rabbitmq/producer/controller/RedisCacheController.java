package com.rabbitmq.producer.controller;

import com.rabbitmq.producer.Entites.Company;
import com.rabbitmq.producer.service.RedisCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redisCache")
public class RedisCacheController {
    @Autowired
    private RedisCacheService redisCacheService;

    @PostMapping("/saveCompany")
    @CachePut(value="company",key = "#company.name")
     public Company saveCompanyDetails(@RequestBody Company company){
        return redisCacheService.saveCompanyDetails(company);
     }
     @GetMapping("/getCompany")
     @Cacheable(value="company",key = "#name")
     public Company getCompanyDetails(@RequestParam  String name){
         long startTime = System.currentTimeMillis();
         Company company = redisCacheService.getCompanyDetails(name);
         long diff = System.currentTimeMillis() - startTime;
         System.out.println("Time Took: for key "+name+" is " +diff+" ms ");
        return company;
     }
}
/*
Time Took: for key venkat shiva is 1390 ms
Time Took: for key venkat shiva is 2 ms
Time Took: for key venkat shiva is 2 ms
Time Took: for key venkat shiva is 3 ms
Time Took: for key venkat shiva is 2 ms
Time Took: for key venkat shiva 11 is 1 ms
Time Took: for key venkat shiva 12 is 2 ms
still didn't used any cache #need to see who is cached spring boot or some one
 */

/*
When multiple cache operations needed.

@Caching(
    evict = {
        @CacheEvict(value="products", key="#id"),
        @CacheEvict(value="inventory", key="#id")
    }
)
------------------- For More Info: GPT( venkatshiva607.com) Redis Project (CacheEvict Cacheable Explantion)
Spring internally executes equivalent logic:

redisTemplate.opsForValue()
             .get("products::100");

or

redisTemplate.opsForValue()
             .set("products::100", product);

You don't see it because CacheManager abstracts it.



Need to see this: bypasses Spring Proxy.

@Cacheable is cache-provider agnostic.
"Spring, before executing this method,
check the configured CacheManager."
 */


/*
these annotations don't use the redis template ,they use cache manager , by default cache manager
uses the jdk serializable , we have to override that , to store objects in the form of strings.
 */


/* if we have cache this time took log is also not visible.(i mean even that line is also not hitting).