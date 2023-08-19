package com.example.unit_test;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Book {
    @GeneratedValue()
    @Id
    private Integer Id ;
    String name;

    public Book(Integer id, String name) {
        Id = id;
        this.name = name;
    }
    public Book() {
    }
}
