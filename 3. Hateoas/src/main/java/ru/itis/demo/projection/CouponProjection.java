package ru.itis.demo.projection;

import org.springframework.data.rest.core.config.Projection;
import ru.itis.demo.models.Coupon;
import ru.itis.demo.models.ProductCategory;

@Projection(name = "couponProjection", types = Coupon.class)
public interface CouponProjection {
    ProductCategory getProductCategory();
    String getName();
    String getDescription();
    Integer getPercent();
    String getCode();
}
