package com.cookingAPI.cookApp.service;

import com.cookingAPI.cookApp.model.RecipeIngredient;
import com.cookingAPI.cookApp.repository.RecipeIngredientRep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class RecipeIngredientService {
    private final RecipeIngredientRep repository;
    public List<RecipeIngredient> findAllRecipeIngredient() {
        return repository.findAll();
    }
    public RecipeIngredient saveRecipeIngredient(RecipeIngredient RI) {
        return repository.save(RI);
    }
}
