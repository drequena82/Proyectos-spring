package io.spring.billing.managers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.spring.billing.entities.Line;
import io.spring.billing.repositories.LineRepository;

@Service
public class LineManager extends AbstractManager<Line>{
	
	@Autowired
	private LineRepository repo;
	
	@Override
	public Line save(Line element) {
		// TODO Auto-generated method stub
		return repo.save(element);
	}
	
	@Override
	public void delete(Line element) {
		// TODO Auto-generated method stub
		repo.delete(element);
	}
	
	@Override
	public Iterable<Line> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	@Override
	public Optional<Line> findOne(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}
	
	@Override
	public LineRepository getInstance() {
		// TODO Auto-generated method stub
		return this.repo;
	}
}
