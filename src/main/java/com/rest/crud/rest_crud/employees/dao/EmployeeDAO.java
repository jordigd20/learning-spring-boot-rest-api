package com.rest.crud.rest_crud.employees.dao;

import com.rest.crud.rest_crud.employees.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();

    Employee findById(Integer id);

    void save(Employee employee);

    Employee update(Employee employee);

    void delete(Employee employee);
}
