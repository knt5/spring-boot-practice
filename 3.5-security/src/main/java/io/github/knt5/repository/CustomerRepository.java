package io.github.knt5.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.github.knt5.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	@Query("SELECT x FROM Customer x ORDER BY x.firstName, x.lastName")
	public List<Customer> findAllOrderByName();
	
	@Query("SELECT x FROM Customer x ORDER BY x.firstName, x.lastName")
	public Page<Customer> findAllOrderByName(Pageable pageable);
	
	@Query("SELECT x FROM Customer x JOIN FETCH x.user ORDER BY x.firstName, x.lastName")
	public List<Customer> findAllWithUserOrderByName();
}
