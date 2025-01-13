package com.tilak.crudWithMapping.services;

import com.tilak.crudWithMapping.entities.Address;
import com.tilak.crudWithMapping.entities.Employee;
import com.tilak.crudWithMapping.repositories.AddressRepository;
import com.tilak.crudWithMapping.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getEmployeesByProjectName(String projectName) {
        return employeeRepository.findEmployeesByProjectName(projectName);
    }

    public Employee saveEmployee(Employee employee) {
//        addressRepository.save(address);
//        employee.setAddress(address);
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    @Transactional
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            employee.setName(employeeDetails.getName());
            employee.setEmail(employeeDetails.getEmail());
            employee.setAddress(employeeDetails.getAddress());
            employee.setDepartment(employeeDetails.getDepartment());
            employee.setProjects(employeeDetails.getProjects());
            return employeeRepository.save(employee);
        }
        return null;
    }
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
    public List<Employee> getEmployeesByName(String name) {
            return employeeRepository.findEmployeesByName(name);
    }
    public List<Employee> getEmployeesByCity(String city) {
            return employeeRepository.findEmployeesByCity(city);
    }

    public List<Employee> getEmployeesByDepartment(String departmentName) {
            return employeeRepository.findEmployeesByDepartmentName(departmentName);
    }

}
