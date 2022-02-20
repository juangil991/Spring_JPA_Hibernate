package com.sofka.SpringJPA.repository;

import com.sofka.SpringJPA.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleJpaRepository extends JpaRepository<Role,Long> {
    List<Role> findByName(String name);
}
