package com.demo.restapis.service;

import com.demo.restapis.entity.Employee;
import com.demo.restapis.rest.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int id) throws EmployeeNotFoundException;

    Employee save(Employee employee);

    void deleteById(int id);
}
