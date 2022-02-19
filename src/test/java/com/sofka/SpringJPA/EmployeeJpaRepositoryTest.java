package com.sofka.SpringJPA;

import com.sofka.SpringJPA.models.Employee;
import com.sofka.SpringJPA.repository.IEmployeeJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeJpaRepositoryTest {
    @Autowired
    private IEmployeeJpaRepository repo;
    @Test
    public void saveEmployee() {
        Employee john=new Employee("John","Smith","empl123");
        Employee claire=new Employee("Claire","Simpson","empl124");
        repo.save(john);
        repo.save(claire);

        repo.flush();

        assertEquals(2,repo.findAll().size());
    }
}
