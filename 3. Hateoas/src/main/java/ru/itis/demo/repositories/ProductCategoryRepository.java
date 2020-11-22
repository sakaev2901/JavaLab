package ru.itis.demo.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.demo.models.ProductCategory;

@RepositoryRestResource
public interface ProductCategoryRepository extends PagingAndSortingRepository<ProductCategory, Long> {
}
