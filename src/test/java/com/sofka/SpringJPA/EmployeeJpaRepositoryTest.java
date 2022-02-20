package com.sofka.SpringJPA;

import com.sofka.SpringJPA.models.Employee;
import com.sofka.SpringJPA.models.Project;
import com.sofka.SpringJPA.models.Role;
import com.sofka.SpringJPA.repository.IEmployeeJpaRepository;
import com.sofka.SpringJPA.repository.IProjectJpaRepository;
import com.sofka.SpringJPA.repository.IRoleJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeJpaRepositoryTest {
    @Autowired
    private IEmployeeJpaRepository repoEmpl;

    @Autowired
    private IProjectJpaRepository repoProj;

    @Autowired
    private IRoleJpaRepository repoRole;

    @Test
    public void saveEmployee() {

        Role admin = new Role("ROLE_ADMIN");
        Role dev = new Role("ROLE_DEV");

        admin = repoRole.save(admin);
        dev = repoRole.save(dev);

        Project proj1 = new Project("proj1");
        Project proj2 = new Project("proj2");
        Project proj3 = new Project("proj3");

        Employee john=new Employee("John","Smith","empl123",dev);
        Employee claire=new Employee("Claire","Simpson","empl124",admin);

        john.getProjects().add(proj1);
        john.getProjects().add(proj2);

        claire.getProjects().add(proj1);
        claire.getProjects().add(proj2);
        claire.getProjects().add(proj3);


        repoEmpl.save(john);
        repoEmpl.save(claire);

        repoEmpl.flush();

        Employee empl124=repoEmpl.findByEmployeeid("empl124");
        assertEquals("Claire",empl124.getFirstName());
        assertEquals(2,repoEmpl.findAll().size());
        assertEquals(admin, empl124.getRole());
    }
}
