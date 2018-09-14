package io.spring.billing.repositories;

import org.springframework.data.repository.CrudRepository;

import io.spring.billing.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{
	public User findByUserName(String userName);
}
