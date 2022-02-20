package com.sofka.SpringJPA.repository;

import com.sofka.SpringJPA.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleJpaRepository extends JpaRepository<Role,Long> {
}
