package com.rest.crud.rest_crud.employees.service;

import com.rest.crud.rest_crud.common.errors.BadRequestException;
import com.rest.crud.rest_crud.common.errors.NotFoundException;
import com.rest.crud.rest_crud.employees.dao.EmployeeDAO;
import com.rest.crud.rest_crud.employees.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Service
//public class OldEmployeeService implements EmployeeService {
public class OldEmployeeService {
    private EmployeeDAO employeeDAO;

    @Autowired
    public OldEmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

//    @Override
    public List<Employee> findAllEmployees() {
        return this.employeeDAO.findAll();
    }

//    @Override
    public Employee findEmployeeById(Integer id) {
        Employee employee = this.employeeDAO.findById(id);

        if (employee == null) {
            throw new NotFoundException("Employee with id: #" + id + " was not found");
        }

        return employee;
    }

//    @Override
    @Transactional
    public Employee createEmployee(Employee employee) {
        this.validateEmployee(employee);
        this.employeeDAO.save(employee);

        return employee;
    }

//    @Override
    @Transactional
    public Employee updateEmployee(Employee employee, Integer employeeId) {
        this.validateEmployee(employee);

        Employee employeeFounded = this.employeeDAO.findById(employeeId);

        if (employeeFounded == null) {
            throw new NotFoundException("Employee with id: #" + employeeId + " was not found");
        }

        employeeFounded.setEmail(employee.getEmail());
        employeeFounded.setFirstName(employee.getFirstName());
        employeeFounded.setLastName(employee.getLastName());
        employeeFounded.setId(employeeId);

        return this.employeeDAO.update(employeeFounded);
    }

//    @Override
    @Transactional
    public String deleteEmployee(Integer employeeId) {

        Employee employee = this.employeeDAO.findById(employeeId);

        if (employee == null) {
            throw new NotFoundException("Employee with id: #" + employeeId + " was not found");
        }

        this.employeeDAO.delete(employee);

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
