package com.cookingAPI.cookApp.service;

import com.cookingAPI.cookApp.model.Cuisine;
import com.cookingAPI.cookApp.repository.CuisineRep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CuisineService {
    private final CuisineRep repository;

    public List<Cuisine> findAllCuisine() {
        return repository.findAll();
    }

    public Cuisine saveCuisine(Cuisine cuisine) {
        return repository.save(cuisine);
    }

    public Cuisine getCuisineById(Long id) {
        Optional<Cuisine> opCuisine = repository.findById(id);
        return opCuisine.orElse(null);
    }

    public Cuisine updateCuisine(Cuisine updatedCuisine) {
        Cuisine cuisine = getCuisineById(updatedCuisine.getId());
        if (cuisine == null) {
            throw new IllegalArgumentException("Кухня с указанным id не найдена");
        }
        return repository.save(updatedCuisine);
    }

    public void deleteCuisine(Long id) {
        Cuisine cuisine = getCuisineById(id);
        if (cuisine == null) {
            throw new IllegalArgumentException("Кухня с указанным id не найдена");
        }
        repository.deleteById(id);
    }
}
