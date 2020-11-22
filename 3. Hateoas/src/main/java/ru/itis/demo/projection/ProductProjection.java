package ru.itis.demo.projection;

import lombok.ToString;
import org.springframework.data.rest.core.config.Projection;
import ru.itis.demo.models.Product;
import ru.itis.demo.models.ProductCategory;

import javax.persistence.ManyToOne;

@Projection(name = "productProjection", types = Product.class)
public interface ProductProjection {
    String getName();
    String getDescription();
    Integer getPrice();
    ProductCategory getProductCategory();
}
