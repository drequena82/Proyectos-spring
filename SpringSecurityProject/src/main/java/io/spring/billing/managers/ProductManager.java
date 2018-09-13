package io.spring.billing.managers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.spring.billing.entities.Product;
import io.spring.billing.repositories.ProductRepository;

@Service
public class ProductManager extends AbstractManager<Product>{
	
	@Autowired
	private ProductRepository repo;
	
	@Override
	public Product save(Product element) {
		// TODO Auto-generated method stub
		return repo.save(element);
	}
	
	@Override
	public void delete(Product element) {
		// TODO Auto-generated method stub
		repo.delete(element);
	}
	
	@Override
	public Iterable<Product> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	@Override
	public Optional<Product> findOne(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}
	
	public Iterable<Product> findByPriceGreaterThanEqual(Double price){
		return this.repo.findByPriceGreaterThanEqual(price);
	}
	
	@Override
	public ProductRepository getInstance() {
		// TODO Auto-generated method stub
		return this.repo;
	}
}
