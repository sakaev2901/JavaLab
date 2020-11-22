package ru.itis.demo.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.demo.models.Product;
import ru.itis.demo.projection.ProductProjection;

@RepositoryRestResource
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
}
