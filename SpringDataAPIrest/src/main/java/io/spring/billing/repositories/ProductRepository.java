package io.spring.billing.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.spring.billing.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

	public List<Product> findByPriceGreaterThanEqual(Double price);
	
}
