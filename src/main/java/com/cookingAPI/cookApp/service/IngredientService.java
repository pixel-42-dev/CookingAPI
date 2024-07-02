package com.cookingAPI.cookApp.service;

import com.cookingAPI.cookApp.model.Ingredient;
import com.cookingAPI.cookApp.repository.IngredientRep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IngredientService {
    private final IngredientRep repository;

    public List<Ingredient> findAllIngredients() {
        return repository.findAll();
    }

    public Ingredient saveIngredient(Ingredient ingredient) {
        return repository.save(ingredient);
    }

    public Ingredient getIngredientById(Long id) {
        Optional<Ingredient> opIngredient = repository.findById(id);
        return opIngredient.orElse(null);
    }

    public Ingredient updateIngredient(Ingredient updatedIngredient) {
        Ingredient ingredient = getIngredientById(updatedIngredient.getId());
        if (ingredient == null) {
            throw new IllegalArgumentException("Ингредиент с указанным id не найден");
        }
        return repository.save(updatedIngredient);
    }

    public void deleteIngredient(Long id) {
        Ingredient ingredient = getIngredientById(id);
        if (ingredient == null) {
            throw new IllegalArgumentException("Ингредиент с указанным id не найден");
        }
        repository.deleteById(id);
    }
}
