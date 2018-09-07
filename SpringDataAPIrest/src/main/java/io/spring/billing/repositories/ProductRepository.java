package io.spring.billing.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import io.spring.billing.entities.Product;

@RepositoryRestResource(path="product")
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>{

	@RestResource(path="productGreaterOrEqual")
	public List<Product> findByPriceGreaterThanEqual(@Param("price")Double price);
	
	@RestResource(exported=false)
	public Optional<Product> findById(Long id);
}
