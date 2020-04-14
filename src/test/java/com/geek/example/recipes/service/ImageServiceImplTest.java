package com.geek.example.recipes.service;

import com.geek.example.recipes.model.Recipe;
import com.geek.example.recipes.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;

class ImageServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    private ImageService imageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        imageService = new ImageServiceImpl(recipeRepository);
    }

    @Test
    void saveImageFile() throws Exception {
        Long id = 1L;

        MultipartFile file = new MockMultipartFile("imageFile", "testing.txt",
                "text/plain", "Spring".getBytes());

        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Mockito.when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        imageService.saveImageFile(id, file);

        Mockito.verify(recipeRepository, Mockito.times(1)).save(argumentCaptor.capture());

        Recipe savedRecipe = argumentCaptor.getValue();
        assertEquals(savedRecipe.getImage().length, file.getBytes().length);
    }
}