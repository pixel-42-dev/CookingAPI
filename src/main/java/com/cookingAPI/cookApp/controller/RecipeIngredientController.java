package com.cookingAPI.cookApp.controller;

import com.cookingAPI.cookApp.model.Category;
import com.cookingAPI.cookApp.model.RecipeIngredient;
import com.cookingAPI.cookApp.service.RecipeIngredientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
public class RecipeIngredientController {
    private final RecipeIngredientService service;

    public List<RecipeIngredient> findAllRecipeIngredient() {
        return service.findAllRecipeIngredient();
    }

    public String saveRecipeIngredient(@RequestBody RecipeIngredient RI) {
        service.saveRecipeIngredient(RI);
        return "связь добавлена!";
    }
}
