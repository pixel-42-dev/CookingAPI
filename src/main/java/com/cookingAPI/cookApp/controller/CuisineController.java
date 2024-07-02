package com.cookingAPI.cookApp.controller;

import com.cookingAPI.cookApp.model.Cuisine;
import com.cookingAPI.cookApp.service.CuisineService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cuisine")
@AllArgsConstructor
public class CuisineController {
    private final CuisineService service;

    @GetMapping
    public ResponseEntity<List<Cuisine>> findAllCuisine() {
        List<Cuisine> cuisines = service.findAllCuisine();
        return new ResponseEntity<>(cuisines, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveCuisine(@RequestBody Cuisine cuisine) {
        service.saveCuisine(cuisine);
        return new ResponseEntity<>("Кухня успешно добавлена", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuisine> getCuisineById(@PathVariable Long id) {
        Cuisine cuisine = service.getCuisineById(id);
        if (cuisine != null) {
            return new ResponseEntity<>(cuisine, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuisine> updateCuisine(@PathVariable Long id, @RequestBody Cuisine updatedCuisine) {
        Cuisine cuisine = service.getCuisineById(id);
        if (cuisine != null) {
            updatedCuisine.setId(id);
            Cuisine updated = service.updateCuisine(updatedCuisine);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuisine(@PathVariable Long id) {
        Cuisine cuisine = service.getCuisineById(id);
        if (cuisine != null) {
            service.deleteCuisine(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
