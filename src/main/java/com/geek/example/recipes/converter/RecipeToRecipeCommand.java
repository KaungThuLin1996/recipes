package com.geek.example.recipes.converter;

import com.geek.example.recipes.command.RecipeCommand;
import com.geek.example.recipes.model.Category;
import com.geek.example.recipes.model.Ingredient;
import com.geek.example.recipes.model.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final NotesToNotesCommand notesConverter;
    private final IngredientToIngredientCommand ingredientConverter;
    private final CategoryToCategoryCommand categoryConverter;

    public RecipeToRecipeCommand(NotesToNotesCommand notesConverter,
                                 IngredientToIngredientCommand ingredientConverter,
                                 CategoryToCategoryCommand categoryConverter) {
        this.notesConverter = notesConverter;
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null) {
            return null;
        }
        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(source.getId());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setImage(source.getImage());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setNotes(notesConverter.convert(source.getNotes()));
        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients().forEach((Ingredient ingredient) ->
                    recipeCommand.getIngredients().add(ingredientConverter.convert(ingredient)));
        }
        if (source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories().forEach((Category category) ->
                    recipeCommand.getCategories().add(categoryConverter.convert(category)));
        }
        return recipeCommand;
    }
}
