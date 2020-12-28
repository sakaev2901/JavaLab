package com.example.demo.hateoas.services;

import com.example.demo.hateoas.dtos.ColorDto;
import com.example.demo.hateoas.model.Car;
import com.example.demo.hateoas.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService{

    @Autowired private CarRepository carRepository;

    @Override
    public void paint(ColorDto colorDto) {
        Car car = carRepository.findById(colorDto.get_id()).orElseThrow(IllegalArgumentException::new);
        car.setColor(colorDto.getColor());
        carRepository.save(car);
    }
}
