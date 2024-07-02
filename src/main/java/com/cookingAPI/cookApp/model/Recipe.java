package com.cookingAPI.cookApp.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "recipe", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "calories")
    private Integer calories;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "time")
    private Integer time;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "cuisine_id")
    private Cuisine cuisine;

    @ManyToOne
    @JoinColumn(name = "difficulty_level_id")
    private DifficultyLevel difficulty;

    @ManyToMany
    @JoinTable(name = "recipe_ingredient",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients = new ArrayList<>();
}