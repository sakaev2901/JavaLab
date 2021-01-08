package ru.itis.querydsl.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itis.querydsl.models.Car;

public interface CarRepository extends MongoRepository<Car, String> {
    Car findFirstByModel(String model);
}
