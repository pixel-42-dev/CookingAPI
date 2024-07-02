package com.cookingAPI.cookApp.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "deifficulty", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class DifficultyLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "examples")
    private String examples;

    @Column(name = "name")
    private String name;
}