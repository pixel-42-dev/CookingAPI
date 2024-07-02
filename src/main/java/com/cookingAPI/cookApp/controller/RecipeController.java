package com.cookingAPI.cookApp.controller;

import com.cookingAPI.cookApp.model.Recipe;
import com.cookingAPI.cookApp.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/recipe")
@AllArgsConstructor
public class RecipeController {

    private final RecipeService service;

    @GetMapping
    public ResponseEntity<List<Recipe>> findAllRecipes() {
        List<Recipe> recipes = service.findAllRecipes();
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
        Recipe recipe = service.getREcipeById(id);
        if (recipe != null) {
            return new ResponseEntity<>(recipe, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveRecipe(@RequestBody Recipe recipe) {
        service.saveRecipe(recipe);
        return new ResponseEntity<>("Рецепт успешно добавлен", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updatePRecipe(@PathVariable Long id, @RequestBody Recipe updatedRecipe) {
        Recipe recipe = service.getREcipeById(id);
        if (recipe != null) {
            updatedRecipe.setId(id);
            Recipe updated = service.updateRecipe(updatedRecipe);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        Recipe recipe = service.getREcipeById(id);
        if (recipe != null) {
            service.deleteRecipe(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/findByIngredientsID")
    public ResponseEntity<List<Recipe>> findByIngredientsID(@RequestBody List<Long> ingredientIds) {
        List<Recipe> recipes = service.findByIngredientsID(ingredientIds);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @PostMapping("/findByIngredientsName")
    public ResponseEntity<List<Recipe>> findByIngredientsName(@RequestBody List<String> ingredientNames) {
        List<Recipe> recipes = service.findByIngredientsName(ingredientNames);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }
}