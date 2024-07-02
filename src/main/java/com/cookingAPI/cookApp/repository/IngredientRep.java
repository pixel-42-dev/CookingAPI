package com.cookingAPI.cookApp.repository;

import com.cookingAPI.cookApp.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRep extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByName(String str);
}