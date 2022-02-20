package com.sofka.SpringJPA.repository;

import com.sofka.SpringJPA.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProjectJpaRepository extends JpaRepository<Project,Long> {

}
