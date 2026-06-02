package com.rabbitmq.producer.Entites;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity(name = "company")
public class Company {
    @Id
    String name;
    String employee;
    int joining;
}
