package com.sofka.SpringJPA.Controllers;

import com.sofka.SpringJPA.Services.ProjectService;
import com.sofka.SpringJPA.models.Project;
import com.sofka.SpringJPA.models.Role;
import com.sofka.SpringJPA.repository.IProjectJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @GetMapping("/projects")
    public ResponseEntity<List<Project>> allProjects(@RequestParam(required = false) String name){
        return projectService.getAllProjects(name);
    }
    @GetMapping("/projects/query")
    public ResponseEntity<List<Project>>allProjectsName(@RequestParam("name") String name){
        return projectService.getAllProjects(name);
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<Project> getProject(@PathVariable("id") Long id){
        return projectService.getProjectById(id);
    }
    @PostMapping("/projects")
    public ResponseEntity<Project> addProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }
    @PutMapping("/projects/{id}")
    public ResponseEntity<Project> updateProjects(@PathVariable("id") long id, @RequestBody Project project) {
        return projectService.updateProject(id,project);
    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity<String> deleteProjectById(@PathVariable("id") long id) {
        return projectService.deleteProject(id);
    }

    @DeleteMapping("/projects")
    public ResponseEntity<HttpStatus> deleteProject() {
        return projectService.deleteAllProjects();
    }
}
