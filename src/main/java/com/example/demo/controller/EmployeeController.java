package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public ResponseEntity<Employee> getEmployeeByName(@RequestParam(required = true,name = "id") String id)
    {
        Employee employee = employeeService.getById(id);
        if (employee!= null){return ResponseEntity.badRequest().build();}
            else return ResponseEntity.ok(employeeService.getById(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<HttpStatus> setSalaryById(@PathVariable("id") String id, @RequestParam(required = true, name = "salary") long salary)
    {
        Employee employee = employeeService.getById(id);
        if (employee!= null){return ResponseEntity.badRequest().build();}
            else employeeService.setSalary(id, salary);
            return ResponseEntity.accepted().build();
    }
}
