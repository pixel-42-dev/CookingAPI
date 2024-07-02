package com.cookingAPI.cookApp.service;

import com.cookingAPI.cookApp.model.*;
import com.cookingAPI.cookApp.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class RecipeService {
    private final RecipeRep repository;
    private final CategoryRep CategoryRepository;
    private final IngredientRep IngredientRepository;
    private final RecipeIngredientRep RIController;
    private final CuisineRep CuisineRepository;
    private final DifficultyRep DifficultyRepository;

    public List<Recipe> findAllRecipes() {
        return repository.findAll();
    }

    public Recipe getREcipeById(Long id) {
        Optional<Recipe> opRecipe = repository.findById(id);
        return opRecipe.orElse(null);
    }
    public Recipe saveRecipe(Recipe recipe) {

        // Проверка категории
        Category category = recipe.getCategory();
        if (category == null) {
            throw new IllegalArgumentException("Не определена категория");
        }
        category = CategoryRepository.findById(category.getId())
                .orElseThrow(() -> new IllegalArgumentException("Категория с указанным id не найдена"));
        recipe.setCategory(category);

        // Проверка кухни
        Cuisine cuisine = recipe.getCuisine();
        if (cuisine == null) {
            throw new IllegalArgumentException("Не определена кухня");
        }
        cuisine = CuisineRepository.findById(cuisine.getId())
                .orElseThrow(() -> new IllegalArgumentException("Кухня с указанным id не найдена"));
        recipe.setCuisine(cuisine);

        // Проверка сложности
        DifficultyLevel difficulty = recipe.getDifficulty();
        if (difficulty == null) {
            throw new IllegalArgumentException("Не определена сложность");
        }
        difficulty = DifficultyRepository.findById(difficulty.getId())
                .orElseThrow(() -> new IllegalArgumentException("Сложность с указанным id не найдена"));
        recipe.setDifficulty(difficulty);


        List<Ingredient> ingredients = recipe.getIngredients();
        for (int i = 0; i < ingredients.size(); i++) {
            if (ingredients.get(i).getId() != null) {           // Если передан id
                Long ingredientId = ingredients.get(i).getId();
                Ingredient ingredient = IngredientRepository.findById(ingredientId)
                        .orElseThrow(() -> new IllegalArgumentException("Ингредиент с указанным id не найден"));
                ingredients.set(i, ingredient);
            } else if (ingredients.get(i).getName() != null) {  // Если передано имя
                String ingredientName = ingredients.get(i).getName();
                List<Ingredient> foundIngredients = IngredientRepository.findByName(ingredientName);
                if (!foundIngredients.isEmpty()) {
                    Ingredient ingredient = foundIngredients.get(0);
                    ingredients.set(i, ingredient);
                } else {
                    throw new IllegalArgumentException("Неизвестные ингредиенты");
                }
            } else {
                throw new IllegalArgumentException("Некорректный формат переданных ингредиентов");
            }
        }

        Set<Ingredient> uniqueIngredients = new HashSet<>(ingredients);
        ingredients.clear();
        ingredients.addAll(uniqueIngredients);

        return repository.save(recipe);
    }

    public Recipe updateRecipe(Recipe updatedRecipe) {
        // Проверка категории
        Category category = updatedRecipe.getCategory();
        if (category == null) {
            throw new IllegalArgumentException("Не определена категория");
        }
        category = CategoryRepository.findById(category.getId())
                .orElseThrow(() -> new IllegalArgumentException("Категория с указанным id не найдена"));
        updatedRecipe.setCategory(category);

        // Проверка кухни
        Cuisine cuisine = updatedRecipe.getCuisine();
        if (cuisine == null) {
            throw new IllegalArgumentException("Не определена кухня");
        }
        cuisine = CuisineRepository.findById(cuisine.getId())
                .orElseThrow(() -> new IllegalArgumentException("Кухня с указанным id не найдена"));
        updatedRecipe.setCuisine(cuisine);

        // Проверка сложности
        DifficultyLevel difficulty = updatedRecipe.getDifficulty();
        if (difficulty == null) {
            throw new IllegalArgumentException("Не определена сложность");
        }
        difficulty = DifficultyRepository.findById(difficulty.getId())
                .orElseThrow(() -> new IllegalArgumentException("Сложность с указанным id не найдена"));
        updatedRecipe.setDifficulty(difficulty);

        return repository.save(updatedRecipe);
    }

    public void deleteRecipe(Long id) {
        repository.deleteById(id);
    }

    public List<Recipe> findByIngredientsID(List<Long> ingredientIds) {
        for (Long ingredientId : ingredientIds) {       // Проверка наличия ингредиентов в БД
            Ingredient ingredient = IngredientRepository.findById(ingredientId)
                    .orElseThrow(() -> new IllegalArgumentException("Ингредиент с id " + ingredientId + " не найден"));
        }

        Set<Long> commonRecipeIds = new HashSet<>();
        if (!ingredientIds.isEmpty()) {
            commonRecipeIds.addAll(RIController.findRecipeIdsByIngredientId(ingredientIds.get(0)));
            for (int i = 1; i < ingredientIds.size(); i++) {
                List<Long> recipeIds = RIController.findRecipeIdsByIngredientId(ingredientIds.get(i));
                commonRecipeIds.retainAll(recipeIds);
            }
        }
        return repository.findAllById(commonRecipeIds);
    }

    public List<Recipe> findByIngredientsName(List<String> ingredientNames) {
        for (String ingredientName : ingredientNames) {     // Проверка наличия ингредиентов в БД
            List<Ingredient> foundIngredients = IngredientRepository.findByName(ingredientName);
            if (foundIngredients.isEmpty()) {
                throw new IllegalArgumentException("Ингредиент с именем " + ingredientName + " не найден");
            }
        }

        Set<Long> commonRecipeIds = new HashSet<>();
        if (!ingredientNames.isEmpty()) {
            for (String ingredientName : ingredientNames) {
                List<Ingredient> foundIngredients = IngredientRepository.findByName(ingredientName);
                if (!foundIngredients.isEmpty()) {
                    Ingredient ingredient = foundIngredients.get(0);
                    List<Long> recipeIds = RIController.findRecipeIdsByIngredientId(ingredient.getId());
                    if (commonRecipeIds.isEmpty()) {
                        commonRecipeIds.addAll(recipeIds);
                    } else {
                        commonRecipeIds.retainAll(recipeIds);
                    }
                }
            }
        }
        return repository.findAllById(commonRecipeIds);
    }
}