package com.sofka.SpringJPA.repository;

import com.sofka.SpringJPA.models.Employee;
import com.sofka.SpringJPA.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmployeeJpaRepository extends JpaRepository<Employee,Long> {
    Employee findByEmployeeid(String employeeid);
    List<Employee>findByLastName(String lastname);
    List<Employee>findByRole(Role role);
}
