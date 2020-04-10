package com.csye7220.recipe.Dao;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csye7220.recipe.model.RecipeEntity;
import com.csye7220.recipe.repository.RecipeRepository;

import java.util.List;

@Service
public class RecipeDaoImpl implements RecipeDao {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    MeterRegistry registry;

    Timer recipeTimer;

    @Override
    public RecipeEntity save(RecipeEntity recipeEntity) {
        recipeTimer = registry.timer("custom.metrics.timer", "Backend", "RecipeSAVE");
        final RecipeEntity[] recipeEntities = new RecipeEntity[1];
        recipeTimer.record(() -> recipeEntities[0] = recipeRepository.save(recipeEntity));
        return recipeEntities[0];
    }

    @Override
    public RecipeEntity getRecipeById(String recipeId) {
        recipeTimer = registry.timer("custom.metrics.timer", "Backend", "RecipeGET");
        final RecipeEntity[] recipeEntities = new RecipeEntity[1];
        recipeTimer.record(() -> recipeEntities[0] =  recipeRepository.findById(recipeId).orElse(null));
        return recipeEntities[0];
    }

    @Override
    public List<RecipeEntity> getAllRecipe() {
        recipeTimer = registry.timer("custom.metrics.timer", "Backend", "RecipeLIST");
        final List<RecipeEntity>[] recipeEntities = new List[1];
        recipeTimer.record(() -> recipeEntities[0] = recipeRepository.findAll());
        return  recipeEntities[0];
    }

    @Override
    public RecipeEntity getLatestRecipe() {
        recipeTimer = registry.timer("custom.metrics.timer", "Backend", "RecipeGETLATEST");
        final RecipeEntity[] recipeEntities = new RecipeEntity[1];
        recipeTimer.record(() -> recipeEntities[0] =   recipeRepository.getLatestRecipe());
        return recipeEntities[0];
    }

    @Override
    public void deleteRecipeById(RecipeEntity recipe) {
        recipeTimer = registry.timer("custom.metrics.timer", "Backend", "RecipeDELETE");
        recipeTimer.record(() -> recipeRepository.delete(recipe));
    }
}
