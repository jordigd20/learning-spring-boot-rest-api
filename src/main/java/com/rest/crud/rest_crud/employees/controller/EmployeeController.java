package com.rest.crud.rest_crud.employees.controller;

import com.rest.crud.rest_crud.employees.entity.Employee;
import com.rest.crud.rest_crud.employees.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.findAllEmployees();
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId) {
        return employeeService.findEmployeeById(employeeId);
    }

    @PutMapping("/{employeeId}")
    public Employee updateEmployee(@RequestBody Employee employee,   @PathVariable int employeeId) {
        return employeeService.updateEmployee(employee, employeeId);
    }

    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        return employeeService.deleteEmployee(employeeId);
    }

}
