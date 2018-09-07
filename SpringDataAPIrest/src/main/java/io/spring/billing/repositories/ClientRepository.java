package io.spring.billing.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import io.spring.billing.entities.Client;

public interface ClientRepository extends PagingAndSortingRepository<Client, Long>,JpaRepository<Client, Long> {

	
	public List<Client> findByNameIgnoreCaseAndSurnameIgnoreCase(@Param("name")String name,@Param("surname")String surname);
	
	public List<Client> findBySurnameIgnoreCaseOrderByNameDesc(@Param("surname") String surname);
}
