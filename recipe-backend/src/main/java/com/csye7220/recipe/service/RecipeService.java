package com.csye7220.recipe.service;

import java.util.List;

import com.csye7220.recipe.model.RecipeEntity;

public interface RecipeService {

    public RecipeEntity saveRecipe(RecipeEntity recipeEntity);

    RecipeEntity getRecipeById(String recipeId);

    List<RecipeEntity> getAllRecipes();

    RecipeEntity getLatestRecipe();

    void deleteRecipeById(RecipeEntity recipe);
}
