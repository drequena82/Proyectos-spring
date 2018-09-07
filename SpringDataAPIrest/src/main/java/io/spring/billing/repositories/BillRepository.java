package io.spring.billing.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import io.spring.billing.entities.Bill;

public interface BillRepository extends PagingAndSortingRepository<Bill, Long>{
	
	public Iterable<Bill> findByClientId(Long id);
	
	@Query("select b from Bill b join fetch b.client c join fetch b.lines l join fetch l.product where b.id=:idBill")
	public Bill fetchByIdWithClientWithLineWithProduct(@Param("idBill") Long id);
	
}
