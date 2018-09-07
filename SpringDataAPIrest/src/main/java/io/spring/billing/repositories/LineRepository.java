package io.spring.billing.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import io.spring.billing.entities.Line;

public interface LineRepository extends PagingAndSortingRepository<Line, Long>{

}
