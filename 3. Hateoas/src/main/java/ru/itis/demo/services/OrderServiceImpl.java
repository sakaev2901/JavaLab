package ru.itis.demo.services;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.demo.models.Cart;
import ru.itis.demo.models.Order;
import ru.itis.demo.models.Product;
import ru.itis.demo.repositories.CartRepository;
import ru.itis.demo.repositories.OrderRepository;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order doOrder() {
        List<Cart> carts = StreamSupport.stream(cartRepository.findAll().spliterator(), false).collect(Collectors.toList());
        Cart cart = carts.get(0);
        Order order = Order.builder()
                .products(new LinkedList<>(cart.getProducts()))
                .price(getPriceWithCoupon(cart))
                .date(new Date())
                .build();
        cart.setProducts(new LinkedList<>());
        cart.setPrice(0);
        cart.setCoupon(null);
        cartRepository.save(cart);
        return orderRepository.save(order);
    }

    private int getPriceWithCoupon(Cart cart) {
        if (cart.getCoupon() != null) {
            if (cart.getCoupon().getProductCategory() == null) {
                return cart.getPrice() * (100 - cart.getCoupon().getPercent()) / 100;
            } else {
                int price = cart.getProducts().stream()
                        .reduce(0, (x, y) -> {
                            if (y.getProductCategory().getName().equals(cart.getCoupon().getProductCategory().getName())) {
                                return x + y.getPrice() * (100 - cart.getCoupon().getPercent()) / 100;
                            } else {
                                return x + y.getPrice();
                            }
                        }, (x,y) -> x + y);
                return price;
            }
        }
        return cart.getPrice();
    }
}
