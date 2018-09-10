package io.spring.billing.managers;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public abstract class AbstractManager<T> {

	private CrudRepository<T, Long> rep;
	
	public void delete(T element) {
		rep.delete(element);
	}
	
	public T save(T element) {
		return rep.save(element);
	}
	
	public Iterable<T> findAll(){
		return rep.findAll();
	}
	
	public Optional<T> findOne(Long id) {
		return rep.findById(id);
	}
	
	public CrudRepository<T, Long> getInstance(){
		return this.rep;
	}
	
}
