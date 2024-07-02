package com.cookingAPI.cookApp.repository;

import com.cookingAPI.cookApp.model.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface RecipeIngredientRep extends JpaRepository<RecipeIngredient, Long> {
    @Query("SELECT ri.recipe.id FROM RecipeIngredient ri WHERE ri.ingredient.id = :ingredientId")
    List<Long> findRecipeIdsByIngredientId(@Param("ingredientId") Long ingredientId);
}
