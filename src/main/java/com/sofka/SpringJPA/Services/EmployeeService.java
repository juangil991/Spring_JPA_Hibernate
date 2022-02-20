package com.sofka.SpringJPA.Services;

import com.sofka.SpringJPA.models.Employee;
import com.sofka.SpringJPA.models.Project;
import com.sofka.SpringJPA.repository.IEmployeeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    IEmployeeJpaRepository repoEmpl;

    public ResponseEntity<List<Employee>> getAllEmployees(String lastname) {
        try {
            List<Employee> employee = new ArrayList<Employee>();

            if (lastname == null)
                repoEmpl.findAll().forEach(employee::add);
            else
                repoEmpl.findByLastName(lastname).forEach(employee::add);

            if (employee.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Employee> getEmployeetById(long id) {
        Optional<Employee> employeeData = repoEmpl.findById(id);

        if (employeeData.isPresent()) {
            return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Employee> createEmployee(Employee employee) {
        try {
            Employee _employee = repoEmpl
                    .save(new Employee(employee.getFirstName(),employee
                            .getLastName(),employee.getEmployeeid(),employee.getRole()));
            return new ResponseEntity<>(_employee, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    public ResponseEntity<Employee> updateEmployee( long id, Employee employee) {
        ResponseEntity<Employee> employeeData =getEmployeetById(id);
        if(employeeData.hasBody()) {
            employeeData.getBody().setFirstName(employee.getFirstName());
            employeeData.getBody().setLastName(employee.getLastName());
            employeeData.getBody().setEmployeeid(employee.getEmployeeid());
            employeeData.getBody().setRole(employee.getRole());

            repoEmpl.save(employeeData.getBody());
        }
        return employeeData;
    }

    public ResponseEntity<String> deleteEmployee(long id) {
        try {
            repoEmpl.deleteById(id);
            return new ResponseEntity<>("EMPLOYEEE DELETE!! ",HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    public ResponseEntity<HttpStatus> deleteAllEmployees() {
        try {
            repoEmpl.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

}
