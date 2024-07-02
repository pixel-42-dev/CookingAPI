package com.cookingAPI.cookApp.repository;

import com.cookingAPI.cookApp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRep  extends JpaRepository<Category, Long> {
}
