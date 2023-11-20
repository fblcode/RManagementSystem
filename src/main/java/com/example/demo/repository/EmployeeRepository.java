package com.example.demo.repository;



import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;
	
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	    
	@Query("SELECT e FROM Employee e WHERE " +
	           "LOWER(e.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	           "LOWER(e.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	           "LOWER(e.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	    List<Employee> search(String keyword);

	    @Query("SELECT e FROM Employee e WHERE " +
	           "LOWER(e.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	           "LOWER(e.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	           "LOWER(e.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	    Page<Employee> search(String keyword, Pageable pageable);
		
}
