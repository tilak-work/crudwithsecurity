package com.tilak.crudWithMapping.repositories;

import com.tilak.crudWithMapping.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query("SELECT e FROM Employee e JOIN e.projects p WHERE p.name = ?1")
    List<Employee> findEmployeesByProjectName(String projectName);


    @Query("SELECT e FROM Employee e WHERE e.name = ?1")
    List<Employee> findEmployeesByName(String name);


    @Query(value = "SELECT e.* FROM employee e JOIN address a ON e.address_id = a.id WHERE a.city = ?1", nativeQuery = true)
    List<Employee> findEmployeesByCity(String city);


    @Query("SELECT e FROM Employee e WHERE e.department.name = ?1")
    List<Employee> findEmployeesByDepartmentName(String departmentName);
}
