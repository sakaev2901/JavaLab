package ru.itis.demo.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.demo.models.Coupon;
import ru.itis.demo.projection.CouponProjection;

import java.util.Optional;

@RepositoryRestResource(excerptProjection = CouponProjection.class)
public interface CouponRepository extends PagingAndSortingRepository<Coupon, Long> {
    Optional<Coupon> findCouponByCode(String code);
}
