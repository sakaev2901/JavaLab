package com.example.demo.hateoas.services;

import com.example.demo.hateoas.dtos.ColorDto;
import org.springframework.stereotype.Service;

public interface CarService {
    void paint(ColorDto colorDto);
}
