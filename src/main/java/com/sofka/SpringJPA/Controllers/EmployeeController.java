package com.sofka.SpringJPA.Controllers;

import com.sofka.SpringJPA.Services.EmployeeService;
import com.sofka.SpringJPA.models.Employee;
import com.sofka.SpringJPA.models.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class EmployeeController {
    @Autowired
    EmployeeService emplService;

    @GetMapping("/Employee")
    public ResponseEntity<List<Employee>> allEmployees(@RequestParam(required = false) String lastname){
        return emplService.getAllEmployees(lastname);
    }
    @GetMapping("/Employee/query")
    public ResponseEntity<List<Employee>>allEmployesLastName(@RequestParam("lastName") String lastname){
        return emplService.getAllEmployees(lastname);
    }

    @GetMapping("/Employee/{id}")
    public ResponseEntity<Employee> getEmploye(@PathVariable("id") Long id){
        return emplService.getEmployeetById(id);
    }
    @PostMapping("/Employee")
    public ResponseEntity<Employee> addEmploye(@RequestBody Employee employee) {
        return emplService.createEmployee(employee);
    }
    @PutMapping("/Employee/{id}")
    public ResponseEntity<Employee> updateEmployees(@PathVariable("id") long id, @RequestBody Employee employee) {
        return emplService.updateEmployee(id,employee);
    }

    @DeleteMapping("/Employee/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") long id) {
        return emplService.deleteEmployee(id);
    }

    @DeleteMapping("/Employee")
    public ResponseEntity<HttpStatus> deleteEmployee() {
        return emplService.deleteAllEmployees();
    }
}
