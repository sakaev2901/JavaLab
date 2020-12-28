package com.example.demo.spring.jpa;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends MongoRepository<Car, String> {
    @Query(value = "{model: \"?model\"}")
    List<Car> findAllByModel(@Param("model") String model);
}
