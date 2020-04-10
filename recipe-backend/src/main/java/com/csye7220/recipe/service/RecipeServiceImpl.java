package com.csye7220.recipe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csye7220.recipe.Dao.RecipeDao;
import com.csye7220.recipe.model.RecipeEntity;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService{

    @Autowired
    RecipeDao recipeDao;

    @Override
    public RecipeEntity saveRecipe(RecipeEntity recipeEntity) {
        return recipeDao.save(recipeEntity);
    }

    @Override
    public RecipeEntity getRecipeById(String recipeId) {
        return recipeDao.getRecipeById(recipeId);
    }

    @Override
    public List<RecipeEntity> getAllRecipes() {
        return recipeDao.getAllRecipe();
    }

    @Override
    public RecipeEntity getLatestRecipe() {
        return recipeDao.getLatestRecipe();
    }

    @Override
    public void deleteRecipeById(RecipeEntity recipe) {
        recipeDao.deleteRecipeById(recipe);
    }
}
