package io.spring.billing.repositories;

import org.springframework.data.repository.CrudRepository;

import io.spring.billing.entities.Line;

public interface LineRepository extends CrudRepository<Line, Long>{

}
