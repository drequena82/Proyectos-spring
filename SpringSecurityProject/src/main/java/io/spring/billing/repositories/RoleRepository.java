package io.spring.billing.repositories;

import org.springframework.data.repository.CrudRepository;

import io.spring.billing.entities.Role;

public interface RoleRepository  extends CrudRepository<Role, Long>{

	public Role findByName(String name);

}
