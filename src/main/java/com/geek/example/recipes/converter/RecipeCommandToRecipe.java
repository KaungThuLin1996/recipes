package com.geek.example.recipes.converter;

import com.geek.example.recipes.command.CategoryCommand;
import com.geek.example.recipes.command.IngredientCommand;
import com.geek.example.recipes.command.RecipeCommand;
import com.geek.example.recipes.model.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final NotesCommandToNotes notesConverter;
    private final IngredientCommandToIngredient ingredientConverter;
    private final CategoryCommandToCategory categoryConverter;

    public RecipeCommandToRecipe(NotesCommandToNotes notesConverter,
                                 IngredientCommandToIngredient ingredientConverter,
                                 CategoryCommandToCategory categoryConverter) {
        this.notesConverter = notesConverter;
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null) {
            return null;
        }
        final Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setDescription(source.getDescription());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setCookTime(source.getCookTime());
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        recipe.setDirections(source.getDirections());
        recipe.setImage(source.getImage());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setNotes(notesConverter.convert(source.getNotes()));
        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients().forEach((IngredientCommand ingredientCommand) ->
                    recipe.getIngredients().add(ingredientConverter.convert(ingredientCommand)));
        }
        if (source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories().forEach((CategoryCommand categoryCommand) ->
                    recipe.getCategories().add(categoryConverter.convert(categoryCommand)));
        }
        return null;
    }
}
