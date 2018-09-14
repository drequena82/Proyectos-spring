package io.spring.billing.repositories;

import org.springframework.data.repository.CrudRepository;

import io.spring.billing.entities.Privilege;

public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {
	public Privilege findByName(String name); 
}
