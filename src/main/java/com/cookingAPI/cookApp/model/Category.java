package com.cookingAPI.cookApp.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "Category", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "examples")
    private String examples;

    @Column(name = "name")
    private String name;
}
