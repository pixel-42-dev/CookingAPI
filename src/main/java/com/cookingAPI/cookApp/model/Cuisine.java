package com.cookingAPI.cookApp.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "Cuisine", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Cuisine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;
}
