package com.demo.restapis.rest;

import com.demo.restapis.dao.EmployeeDAO;
import com.demo.restapis.entity.Employee;
import com.demo.restapis.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    //inject dao
    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService thEmployeeService) {
        employeeService = thEmployeeService;
    }
    //expose "/employee"
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }
    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> findById(@PathVariable int employeeId) {
        Employee theEmployee = null;
        try {
            theEmployee = employeeService.findById(employeeId);
        } catch (EmployeeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hapatikani");
        }


        return ResponseEntity.ok(theEmployee);
    }

    @PostMapping(value="/employees", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Employee addEmployee(@RequestBody Employee theEmployee){
        theEmployee.setId(0);

        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    //add a put mapping
    @PutMapping("/emloyees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) throws EmployeeNotFoundException {

        Employee theEmployee = employeeService.findById(employeeId);

        if(theEmployee == null) {
            //throw new EmployeeNotFoundException("Employee not found" + employeeId);
            return "Employee not found" + employeeId;
        }

        employeeService.deleteById(employeeId);

        return "Deleted employee id - " + employeeId;
    }



}
