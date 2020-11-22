package ru.itis.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.demo.dtos.CouponDto;
import ru.itis.demo.dtos.TestDto;
import ru.itis.demo.models.Coupon;
import ru.itis.demo.services.CartService;

@RepositoryRestController
public class CartController {

    @Autowired
    private CartService cartService;

    @DeleteMapping("/carts/deleteAll")
    public ResponseEntity<?> deleteAll() {
        return ResponseEntity.ok(EntityModel.of(cartService.deleteAllProductsFromCart()));
    }

    @DeleteMapping("/carts/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(EntityModel.of(cartService.deleteProductFromCart(productId)));
    }

    @PutMapping("/carts/coupon")
    public ResponseEntity<?> applyCoupon(@RequestBody CouponDto couponDto) {
        System.out.println(couponDto);
        return ResponseEntity.ok(EntityModel.of(cartService.applyCoupon(couponDto.getCode())));
    }
}
