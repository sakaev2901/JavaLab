package ru.itis.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.demo.models.Cart;
import ru.itis.demo.models.Coupon;
import ru.itis.demo.models.Product;
import ru.itis.demo.repositories.CartRepository;
import ru.itis.demo.repositories.CouponRepository;
import ru.itis.demo.repositories.ProductRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CouponRepository couponRepository;

    @Override
    public Product addProductToCart(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(IllegalArgumentException::new);
        List<Cart> carts = StreamSupport.stream(cartRepository.findAll().spliterator(), false).collect(Collectors.toList());
        Cart cart = carts.get(0);
        cart.addProduct(product);
        Cart cart1 = cartRepository.save(cart);
        System.out.println(cart1);
        return product;
    }

    @Override
    public Product deleteProductFromCart(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(IllegalArgumentException::new);
        List<Cart> carts = StreamSupport.stream(cartRepository.findAll().spliterator(), false).collect(Collectors.toList());
        Cart cart = carts.get(0);
        cart.setProducts(cart.getProducts().stream()
                .filter(item -> !item.getId().equals(product.getId()))
                .collect(Collectors.toList()));
        cartRepository.save(cart);
        return product;
    }

    @Override
    public Cart deleteAllProductsFromCart() {
        List<Cart> carts = StreamSupport.stream(cartRepository.findAll().spliterator(), false).collect(Collectors.toList());
        Cart cart = carts.get(0);
        cart.setProducts(new LinkedList<>());
        cartRepository.save(cart);
        return cart;
    }

    @Override
    public Coupon applyCoupon(String couponCode) {
        List<Cart> carts = StreamSupport.stream(cartRepository.findAll().spliterator(), false).collect(Collectors.toList());
        Cart cart = carts.get(0);
        Coupon coupon = couponRepository.findCouponByCode(couponCode).orElseThrow(() -> new NoSuchElementException(couponCode));
        cart.setCoupon(coupon);
        Cart newCart = cartRepository.save(cart);
        System.out.println(newCart);
        return coupon;
    }
}
