package io.spring.billing.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.spring.billing.entities.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long>{

	public List<Product> findByPriceGreaterThanEqual(Double price);
	
}
