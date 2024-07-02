package com.cookingAPI.cookApp.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "ingredient", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "calories")
    private Long calories;

    @Column(name = "name")
    private String name;
}
