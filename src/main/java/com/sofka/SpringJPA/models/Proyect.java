package com.sofka.SpringJPA.models;

import javax.persistence.*;

@Entity
public class Proyect {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(length = 25,nullable = false,unique = true)
    private String name;

    public Proyect() {
    }

    public Proyect(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
