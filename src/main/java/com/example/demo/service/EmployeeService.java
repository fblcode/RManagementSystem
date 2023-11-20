package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Employee;

public interface EmployeeService {
	public List<Employee> getAllEmployees(String keyword);
	
	void saveEmployee(Employee employee);
	
	Employee getEmployeeById(Integer id);
	
	void deleteEmployeeById(Integer id);
	
	Double calculateAverageSalary();
	
	Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, String keyword);
}

