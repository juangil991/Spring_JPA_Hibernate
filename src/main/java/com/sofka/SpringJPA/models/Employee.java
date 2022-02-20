package com.sofka.SpringJPA.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 25, nullable = false)
    private String firstName;

    @Column(length = 25, nullable = false)
    private String lastName;

    @Column(length = 25, nullable = false)
    private String employeeid;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_role")
    private Role role;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "employee_project",
            joinColumns = {@JoinColumn(name="employee_id")},
            inverseJoinColumns = {@JoinColumn(name ="project_id")})
    private List<Project> projects=new ArrayList<Project>();

    public Employee() {
    }

    public Employee(String firstName, String lastName, String employeeid, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeid = employeeid;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }

    public List<Project>getProjects(){
        return projects;
    }

    public void setProjects(List<Project>projects){
        this.projects=projects;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", employeeid='" + employeeid + '\'' +
                '}';
    }
}
