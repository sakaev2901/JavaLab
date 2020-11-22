package ru.itis.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.demo.services.OrderService;

@RepositoryRestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<?> doOrder() {
        return ResponseEntity.ok(EntityModel.of(orderService.doOrder()));
    }
}
