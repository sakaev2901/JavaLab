package ru.itis.demo.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.demo.models.Order;
import ru.itis.demo.projection.OrderProjection;

@RepositoryRestResource(excerptProjection = OrderProjection.class)
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
}
