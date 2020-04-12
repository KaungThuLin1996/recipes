package com.geek.example.recipes.service;

import com.geek.example.recipes.command.RecipeCommand;
import com.geek.example.recipes.model.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
    Recipe findById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    RecipeCommand findCommandById(Long id);
    void deleteById(Long idToDelete);

}
