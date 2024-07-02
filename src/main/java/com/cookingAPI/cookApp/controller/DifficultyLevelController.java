package com.cookingAPI.cookApp.controller;

import com.cookingAPI.cookApp.model.DifficultyLevel;
import com.cookingAPI.cookApp.service.DifficultyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/difficult")
@AllArgsConstructor
public class DifficultyLevelController {
    private final DifficultyService service;

    @GetMapping
    public ResponseEntity<List<DifficultyLevel>> findAllDifficulty() {
        List<DifficultyLevel> difficultyLevels = service.findAllDifficulty();
        return new ResponseEntity<>(difficultyLevels, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveDifficulty(@RequestBody DifficultyLevel difficulty) {
        service.saveDifficulty(difficulty);
        return new ResponseEntity<>("Сложность успешно добавлена", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DifficultyLevel> getDifficultyById(@PathVariable Long id) {
        DifficultyLevel difficulty = service.getDifficultyById(id);
        if (difficulty != null) {
            return new ResponseEntity<>(difficulty, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DifficultyLevel> updateDifficulty(@PathVariable Long id, @RequestBody DifficultyLevel updatedDifficulty) {
        DifficultyLevel difficulty = service.getDifficultyById(id);
        if (difficulty != null) {
            updatedDifficulty.setId(id);
            DifficultyLevel updated = service.updateDifficulty(updatedDifficulty);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDifficulty(@PathVariable Long id) {
        DifficultyLevel difficulty = service.getDifficultyById(id);
        if (difficulty != null) {
            service.deleteDifficulty(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
