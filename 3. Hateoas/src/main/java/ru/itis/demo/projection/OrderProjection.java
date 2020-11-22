package ru.itis.demo.projection;

import org.springframework.data.rest.core.config.Projection;
import ru.itis.demo.models.Order;
import ru.itis.demo.models.Product;

import java.util.Date;
import java.util.List;

@Projection(name = "orderProjection", types = Order.class)
public interface OrderProjection {
    Integer getId();
    List<Product> getProducts();
    Date getDate();
    Integer getPrice();
}
