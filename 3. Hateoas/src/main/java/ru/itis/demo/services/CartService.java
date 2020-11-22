package ru.itis.demo.services;

import ru.itis.demo.models.Cart;
import ru.itis.demo.models.Coupon;
import ru.itis.demo.models.Product;

public interface CartService {

    Product addProductToCart(Long productId);
    Product deleteProductFromCart(Long productId);
    Cart deleteAllProductsFromCart();
    Coupon applyCoupon(String couponCode);
}
