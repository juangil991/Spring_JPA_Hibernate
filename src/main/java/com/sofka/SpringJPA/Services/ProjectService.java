package com.sofka.SpringJPA.Services;

import com.sofka.SpringJPA.models.Project;
import com.sofka.SpringJPA.models.Role;
import com.sofka.SpringJPA.repository.IProjectJpaRepository;
import com.sofka.SpringJPA.repository.IRoleJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    IProjectJpaRepository repoProject;

    public ResponseEntity<List<Project>> getAllProjects(String name) {
        try {
            List<Project> projects = new ArrayList<Project>();

            if (name == null)
                repoProject.findAll().forEach(projects::add);
            else
                repoProject.findByName(name).forEach(projects::add);

            if (projects.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(projects, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Project> getProjectById(long id) {
        Optional<Project> projectData = repoProject.findById(id);

        if (projectData.isPresent()) {
            return new ResponseEntity<>(projectData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Project> createProject(Project project) {
        try {
            Project _project = repoProject.save(new Project(project.getName()));
            return new ResponseEntity<>(_project, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    public ResponseEntity<Project> updateProject( long id, Project project) {
        ResponseEntity<Project> projectData =getProjectById(id);
        if(projectData.hasBody()) {
            projectData.getBody().setName(project.getName());

            repoProject.save(projectData.getBody());
        }
        return projectData;
    }

    public ResponseEntity<String> deleteProject(long id) {
        try {
            repoProject.deleteById(id);
            return new ResponseEntity<>("PROJECT DELETE!! ",HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    public ResponseEntity<HttpStatus> deleteAllProjects() {
        try {
            repoProject.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }
}
