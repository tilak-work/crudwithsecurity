package com.tilak.crudWithMapping.services;

import com.tilak.crudWithMapping.entities.Department;
import com.tilak.crudWithMapping.entities.Employee;
import com.tilak.crudWithMapping.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department) {
//        List<Employee> employees=new ArrayList<>();
//        department.setEmployees(employees);
        return departmentRepository.save(department);
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
}