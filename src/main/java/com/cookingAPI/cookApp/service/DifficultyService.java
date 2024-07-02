package com.cookingAPI.cookApp.service;

import com.cookingAPI.cookApp.model.DifficultyLevel;
import com.cookingAPI.cookApp.repository.DifficultyRep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DifficultyService {
    private final DifficultyRep repository;

    public List<DifficultyLevel> findAllDifficulty() {
        return repository.findAll();
    }

    public DifficultyLevel saveDifficulty(DifficultyLevel difficulty) {
        return repository.save(difficulty);
    }

    public DifficultyLevel getDifficultyById(Long id) {
        Optional<DifficultyLevel> opDifficulty = repository.findById(id);
        return opDifficulty.orElse(null);
    }

    public DifficultyLevel updateDifficulty(DifficultyLevel updatedDifficulty) {
        DifficultyLevel difficulty = getDifficultyById(updatedDifficulty.getId());
        if (difficulty == null) {
            throw new IllegalArgumentException("Сложность с указанным id не найдена");
        }
        return repository.save(updatedDifficulty);
    }

    public void deleteDifficulty(Long id) {
        DifficultyLevel difficulty = getDifficultyById(id);
        if (difficulty == null) {
            throw new IllegalArgumentException("Сложность с указанным id не найдена");
        }
        repository.deleteById(id);
    }
}
