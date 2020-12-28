package com.example.demo.spring.jpa;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MongoRepoConfig.class);
        CarRepository carRepository = applicationContext.getBean(CarRepository.class);

        Car car = Car.builder()
                .model("bmw")
                .color("black")
                .costInDollars(50000)
                .build();
        carRepository.save(car); // insert

//        carRepository.delete(Car.builder()._id("5fea32257524d46a7af1b8aa").build()); // delete
        List<Car> cars = carRepository.findAllByModel("bmw");
        cars.forEach(t -> { // update
            t.setColor("pink");
            carRepository.save(t);
        });
    }
}
