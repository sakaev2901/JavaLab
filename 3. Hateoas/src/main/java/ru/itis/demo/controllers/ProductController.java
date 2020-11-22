package ru.itis.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import ru.itis.demo.services.CartService;

@RepositoryRestController
public class ProductController {

    @Autowired
    private CartService cartService;

    @PutMapping("/products/{productId}/addToCart")
    public ResponseEntity<?> addToCart(@PathVariable Long productId) {
        return ResponseEntity.ok(EntityModel.of(cartService.addProductToCart(productId)));
    }


}
