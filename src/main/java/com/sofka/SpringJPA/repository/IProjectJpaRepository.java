package com.sofka.SpringJPA.repository;

import com.sofka.SpringJPA.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProjectJpaRepository extends JpaRepository<Project,Long> {
  List<Project> findByName(String name);
}
