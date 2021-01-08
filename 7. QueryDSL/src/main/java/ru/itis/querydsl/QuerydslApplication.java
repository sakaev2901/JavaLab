package ru.itis.querydsl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itis.querydsl.models.Car;
import ru.itis.querydsl.models.User;
import ru.itis.querydsl.repositories.CarRepository;
import ru.itis.querydsl.repositories.UserRepository;

@SpringBootApplication
public class QuerydslApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(QuerydslApplication.class, args);
        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        CarRepository carRepository = applicationContext.getBean(CarRepository.class);

//        User user = userRepository.findById("5ff8588ffdffe77af0a8d5bf").get();
//        Car car = Car.builder().model("Tesla").build();
//        car = carRepository.save(car);
//        user.getCars().add(car);
//        userRepository.save(user);

//        User user2 = userRepository.findById("5ff8517ffe06e4705ae594d5").get();
//        Car car = carRepository.findFirstByModel("Tesla");
//        user2.getCars().add(car);
//        userRepository.save(user2);

    }

}
