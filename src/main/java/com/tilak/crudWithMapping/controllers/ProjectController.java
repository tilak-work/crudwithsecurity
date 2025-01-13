package com.tilak.crudWithMapping.controllers;

import com.tilak.crudWithMapping.entities.Project;
import com.tilak.crudWithMapping.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // Get projects by employee name
    @GetMapping("/employee/{employeeName}")
    public List<Project> getProjectsByEmployeeName(@PathVariable String employeeName) {
        return projectService.getProjectsByEmployeeName(employeeName);
    }


    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectService.saveProject(project);
    }


    @GetMapping("/{id}")
    public Project getProject(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }


    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }


    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project projectDetails) {
        return projectService.updateProject(id, projectDetails);
    }


    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}
