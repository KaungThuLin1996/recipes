package com.geek.example.recipes.repository;

import com.geek.example.recipes.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
