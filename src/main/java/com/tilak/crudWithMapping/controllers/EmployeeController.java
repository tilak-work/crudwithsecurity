package com.tilak.crudWithMapping.controllers;

import com.tilak.crudWithMapping.entities.Address;
import com.tilak.crudWithMapping.entities.Employee;
import com.tilak.crudWithMapping.services.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        //System.err.println(employee);
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/session")
    public String getSessionId(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession(false);
        return session != null ? session.getId() : "No session created";
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        return employeeService.updateEmployee(id, employeeDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
    @GetMapping("/project/{projectName}")
    public List<Employee> getEmployeesByProjectName(@PathVariable String projectName) {
        return employeeService.getEmployeesByProjectName(projectName);
    }

    @GetMapping("/name/{name}")
    public List<Employee> getEmployeesByName(@PathVariable String name) {
        return employeeService.getEmployeesByName(name);
    }

    @GetMapping("/city/{city}")
    public List<Employee> getEmployeesByCity(@PathVariable String city) {
        return employeeService.getEmployeesByCity(city);
    }

    @GetMapping("/department/{name}")
    public List<Employee> getEmployeesByDepartment(@PathVariable String name) {
        return employeeService.getEmployeesByDepartment(name);
    }
}
