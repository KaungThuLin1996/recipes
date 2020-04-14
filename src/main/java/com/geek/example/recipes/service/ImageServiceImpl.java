package com.geek.example.recipes.service;

import com.geek.example.recipes.model.Recipe;
import com.geek.example.recipes.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Transactional
    @Override
    public void saveImageFile(Long recipeId, MultipartFile file) {
        Optional<Recipe> recipeOpt = recipeRepository.findById(recipeId);
        if (!recipeOpt.isPresent()) {
            throw new RuntimeException("Recipe cannot be null!");
        }
        Recipe recipe = recipeOpt.get();
        try {
            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()) {
                byteObjects[i++] = b;
            }
            recipe.setImage(byteObjects);
            recipeRepository.save(recipe);
        } catch (IOException e) {
            log.error("Error occurred", e);
            e.printStackTrace();
        }
    }
}
