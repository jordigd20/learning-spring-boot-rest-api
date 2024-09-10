package com.rest.crud.rest_crud.employees.dao;

import com.rest.crud.rest_crud.employees.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
