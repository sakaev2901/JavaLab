package com.example.demo.template;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MongoConfig.class);
        MongoTemplate template = applicationContext.getBean(MongoTemplate.class);
        Car car = Car.builder()
                .model("bmw")
                .color("black")
                .costInDollars(50000)
                .build();
        template.save(car); // insert
        Car carToRemove = Car.builder()._id("5fea2c6a75915c1d7e55cabc").build();
        template.remove(carToRemove); // delete
        Car searchedCar = template.findOne(new Query(where("model").is("bmw")), Car.class, "car");
        searchedCar.setColor("white");
        template.save(searchedCar); // update
    }
}
