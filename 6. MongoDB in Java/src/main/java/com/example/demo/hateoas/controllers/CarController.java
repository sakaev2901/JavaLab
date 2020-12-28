package com.example.demo.hateoas.controllers;

import com.example.demo.hateoas.dtos.ColorDto;
import com.example.demo.hateoas.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

    @Autowired private CarService carService;

    @PostMapping("/paint")
    public void paintCar(ColorDto colorDto) {
        System.out.println(colorDto);
        carService.paint(colorDto);
    }

    @GetMapping("/test")
    public String getString() {
        return "test";
    }
}
