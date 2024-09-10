package com.rest.crud.rest_crud.employees.service;

import com.rest.crud.rest_crud.employees.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAllEmployees();

    Employee findEmployeeById(Integer id);

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Employee employee, Integer employeeId);

    String deleteEmployee(Integer id);
}
