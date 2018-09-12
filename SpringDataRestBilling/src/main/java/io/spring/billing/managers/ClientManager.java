package io.spring.billing.managers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.spring.billing.entities.Client;
import io.spring.billing.repositories.ClientRepository;

@Service
public class ClientManager extends AbstractManager<Client> {
	
	@Autowired
	private ClientRepository repo;
	
	@Override
	public Client save(Client element) {
		// TODO Auto-generated method stub
		return repo.save(element);
	}
	
	@Override
	public void delete(Client element) {
		// TODO Auto-generated method stub
		repo.delete(element);
	}
	
	@Override
	public Iterable<Client> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	@Override
	public Optional<Client> findOne(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id);
	}
	
	/*
	public Iterable<Client> findByFilter(ClientFilter filter){
		
		final Specifications<Client> spec = Specifications
				.where(null)
				.and((filter.getNameFilter() == null ? null : ClientSpecs.hasName(filter.getNameFilter())))
				.and((filter.getSurnameFilter() == null ? null : ClientSpecs.hasSurname(filter.getSurnameFilter())))
				.and((filter.getEmailFilter() == null ? null : ClientSpecs.hasEmail(filter.getEmailFilter())));
		
		return null;
	}
	*/
	
	/**
	 * Método creado por nosotros customizado
	 * @param name
	 * @param surname
	 * @return
	 */
	public Iterable<Client> findByNameIgnoreCaseAndSurnameIgnoreCase(String name,String surname){
		return this.repo.findByNameIgnoreCaseAndSurnameIgnoreCase(name, surname);
	}
	
	/**
	 * 
	 * @param surname
	 * @return
	 */
	public Iterable<Client> findBySurnameIgnoreCaseOrderByNameDesc(String surname){
		return this.repo.findBySurnameIgnoreCaseOrderByNameDesc(surname);
	}
	
	@Override
	public ClientRepository getInstance() {
		// TODO Auto-generated method stub
		return this.repo;
	}

}
