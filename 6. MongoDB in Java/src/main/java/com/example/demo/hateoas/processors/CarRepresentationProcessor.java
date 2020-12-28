package com.example.demo.hateoas.processors;

import com.example.demo.hateoas.controllers.CarController;
import com.example.demo.hateoas.model.Car;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CarRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Car>> {
    @Override
    public EntityModel<Car> process(EntityModel<Car> model) {
        model.add(Link.of("http://localhost:80/paint", "paint"));
        return model;
    }
}
