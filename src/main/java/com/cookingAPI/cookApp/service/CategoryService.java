package com.cookingAPI.cookApp.service;

import com.cookingAPI.cookApp.model.Category;
import com.cookingAPI.cookApp.repository.CategoryRep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRep repository;
    public List<Category> findAllCategory() {
        return repository.findAll();
    }
    public Category saveCategory(Category category) {
        return repository.save(category);
    }

    public Category getCategoryById(Long id) {
        Optional<Category> opCategory = repository.findById(id);
        return opCategory.orElse(null);
    }
    public Category updateCategory(Category updatedCategory) {
        Category category = getCategoryById(updatedCategory.getId());
        if (category == null) {
            throw new IllegalArgumentException("Категория с указанным id не найдена");
        }
        return repository.save(updatedCategory);
    }

    public void deleteCategory(Long id) {
        Category category = getCategoryById(id);
        if (category == null) {
            throw new IllegalArgumentException("Категория с указанным id не найдена");
        }
        repository.deleteById(id);
    }
}
