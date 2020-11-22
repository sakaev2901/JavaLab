package ru.itis.demo.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.demo.models.Cart;

@RepositoryRestResource
public interface CartRepository extends PagingAndSortingRepository<Cart, Long> {
}
