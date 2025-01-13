package com.tilak.crudWithMapping.repositories;

import com.tilak.crudWithMapping.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    @Query("SELECT p FROM Project p JOIN p.employees e WHERE e.name = ?1")
    List<Project> findProjectsByEmployeeName(String employeeName);
}
