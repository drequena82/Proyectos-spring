package io.spring.billing.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import io.spring.billing.entities.Client;

public interface ClientRepository extends PagingAndSortingRepository<Client, Long>,JpaRepository<Client, Long> {

	
	public List<Client> findByNameIgnoreCaseAndSurnameIgnoreCase(String name,String surname);
	
	public List<Client> findBySurnameIgnoreCaseOrderByNameDesc(String surname);
}
