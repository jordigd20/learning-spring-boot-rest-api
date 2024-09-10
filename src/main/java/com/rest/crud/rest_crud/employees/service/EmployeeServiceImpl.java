package com.rest.crud.rest_crud.employees.service;

import com.rest.crud.rest_crud.common.errors.BadRequestException;
import com.rest.crud.rest_crud.common.errors.NotFoundException;
import com.rest.crud.rest_crud.employees.dao.EmployeeRepository;
import com.rest.crud.rest_crud.employees.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isEmpty()) {
            throw new NotFoundException("Employee with id: #" + id + " was not found");
        }

        return employee.get();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        this.validateEmployee(employee);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee, Integer employeeId) {
        this.validateEmployee(employee);

        Employee foundedEmployee = this.findEmployeeById(employeeId);

        employee.setId(foundedEmployee.getId());

        return employeeRepository.save(employee);
    }

    @Override
    public String deleteEmployee(Integer employeeId) {
        Employee foundedEmployee = this.findEmployeeById(employeeId);

        employeeRepository.delete(foundedEmployee);

        return "The employee with id: #" + employeeId + " was successfully deleted";
    }

    private void validateEmployee(Employee employee) {
        if (employee.getFirstName() == null) {
            throw new BadRequestException("You must provide a firstName");
        }

        if (employee.getLastName() == null) {
            throw new BadRequestException("You must provide a lastName");
        }

        if (employee.getEmail() == null) {
            throw new BadRequestException("You must provide an email");
        }
    }
}
