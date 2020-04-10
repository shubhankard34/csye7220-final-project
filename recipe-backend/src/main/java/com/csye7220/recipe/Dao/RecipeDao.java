package com.csye7220.recipe.Dao;

import java.util.List;

import com.csye7220.recipe.model.RecipeEntity;

public interface RecipeDao {
    RecipeEntity save(RecipeEntity recipeEntity);

    RecipeEntity getRecipeById(String recipeId);

    List<RecipeEntity> getAllRecipe();

    RecipeEntity getLatestRecipe();

    void deleteRecipeById(RecipeEntity recipe);
}
