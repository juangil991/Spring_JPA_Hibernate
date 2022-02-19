package com.sofka.SpringJPA.repository;

import com.sofka.SpringJPA.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeJpaRepository extends JpaRepository<Employee,Long> {
    Employee findByEmployeeid(String employeeid);
}
