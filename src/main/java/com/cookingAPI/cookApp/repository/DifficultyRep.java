package com.cookingAPI.cookApp.repository;

import com.cookingAPI.cookApp.model.DifficultyLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DifficultyRep  extends JpaRepository<DifficultyLevel, Long> {
}
