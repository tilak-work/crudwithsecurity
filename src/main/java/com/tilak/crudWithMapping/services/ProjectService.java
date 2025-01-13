package com.tilak.crudWithMapping.services;

import com.tilak.crudWithMapping.entities.Project;
import com.tilak.crudWithMapping.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getProjectsByEmployeeName(String employeeName) {
        return projectRepository.findProjectsByEmployeeName(employeeName);
    }


    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }


    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }


    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }


    public Project updateProject(Long id, Project projectDetails) {
        Project project = getProjectById(id);
        if (project != null) {
            project.setName(projectDetails.getName());
            return projectRepository.save(project);
        }
        return null;
    }


    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
