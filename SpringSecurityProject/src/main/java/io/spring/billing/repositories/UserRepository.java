package io.spring.billing.repositories;

import org.springframework.data.repository.CrudRepository;

import io.spring.billing.entities.UserBilling;

public interface UserRepository extends CrudRepository<UserBilling, Long>{

}
