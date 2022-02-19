package com.sofka.SpringJPA.repository;

import com.sofka.SpringJPA.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeJpaRepository extends JpaRepository<Employee,Long> {
    Employee findByEmployeeid(String employeeid);
}
