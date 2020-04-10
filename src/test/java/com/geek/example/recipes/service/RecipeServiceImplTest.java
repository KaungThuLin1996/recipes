package com.geek.example.recipes.service;

import com.geek.example.recipes.model.Recipe;
import com.geek.example.recipes.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    private RecipeServiceImpl recipeServiceImpl;
    @Mock
    private RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeServiceImpl = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void getRecipes() {
        HashSet<Recipe> recipeData = new HashSet<>();
        recipeData.add(new Recipe());
        when(recipeRepository.findAll()).thenReturn(recipeData);
        Set<Recipe> recipes = recipeServiceImpl.getRecipes();
        assertEquals(1, recipes.size());
        verify(recipeRepository, times(1)).findAll();
    }
}