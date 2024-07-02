package com.cookingAPI.cookApp.repository;

import com.cookingAPI.cookApp.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRep extends JpaRepository<Recipe, Long> {

}
