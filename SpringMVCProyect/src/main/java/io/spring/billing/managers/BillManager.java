package io.spring.billing.managers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.spring.billing.entities.Bill;
import io.spring.billing.repositories.BillRepository;

@Service
public class BillManager extends AbstractManager<Bill>{
	
	@Autowired
	private BillRepository repo;
	@Override
	public Bill save(Bill element) {
		// TODO Auto-generated method stub
		return repo.save(element);
	}
	
	@Override
	public void delete(Bill element) {
		// TODO Auto-generated method stub
		repo.delete(element);
	}
	
	@Override
	public Iterable<Bill> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	@Override
	public Optional<Bill> findOne(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}
	
	public Iterable<Bill> findByClientId(Long id){
		return this.repo.findByClientId(id);
	}
	
	public Bill fetchByIdWithClientWithLineWithProduct(Long id) {
		return this.repo.fetchByIdWithClientWithLineWithProduct(id);
	}
	
	public List<Bill>fetchByClientIdWithLineWithProduct(Long id) {
		return this.repo.fetchByClientIdWithLineWithProduct(id);
	}
	@Override
	public BillRepository getInstance() {
		// TODO Auto-generated method stub
		return this.repo;
	}
}
