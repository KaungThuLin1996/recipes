package com.geek.example.recipes.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryTest {

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
    }

    @Test
    void getId() {
        Long idValue = 4L;
        category.setId(4L);
        assertEquals(idValue, category.getId());
    }

    @Test
    void getCategoryName() {
    }

    @Test
    void getRecipes() {
    }
}