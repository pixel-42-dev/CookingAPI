package com.cookingAPI.cookApp.repository;

import com.cookingAPI.cookApp.model.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuisineRep  extends JpaRepository<Cuisine, Long> {
}
