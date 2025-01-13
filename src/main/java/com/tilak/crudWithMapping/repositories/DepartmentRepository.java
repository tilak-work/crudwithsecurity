package com.tilak.crudWithMapping.repositories;

import com.tilak.crudWithMapping.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

    @Query("SELECT d FROM Department d WHERE d.name = ?1")
    Department findDepartmentByName(String name);
}
