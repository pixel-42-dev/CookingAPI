package com.cookingAPI.cookApp.controller;

import com.cookingAPI.cookApp.model.Ingredient;
import com.cookingAPI.cookApp.service.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ingredient")
@AllArgsConstructor
public class IngredientController {
    private final IngredientService service;

    @GetMapping
    public ResponseEntity<List<Ingredient>> findAllIngredients() {
        List<Ingredient> ingredients = service.findAllIngredients();
        return new ResponseEntity<>(ingredients, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveIngredient(@RequestBody Ingredient ingredient) {
        service.saveIngredient(ingredient);
        return new ResponseEntity<>("Ингредиент успешно добавлен", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable Long id) {
        Ingredient ingredient = service.getIngredientById(id);
        if (ingredient != null) {
            return new ResponseEntity<>(ingredient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable Long id, @RequestBody Ingredient updatedIngredient) {
        Ingredient ingredient = service.getIngredientById(id);
        if (ingredient != null) {
            updatedIngredient.setId(id);
            Ingredient updated = service.updateIngredient(updatedIngredient);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
        Ingredient ingredient = service.getIngredientById(id);
        if (ingredient != null) {
            service.deleteIngredient(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

