package com.csye7220.recipe.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.csye7220.recipe.model.RecipeEntity;

import java.util.List;

@Repository
@Transactional
public interface RecipeRepository  extends JpaRepository<RecipeEntity, String> {
    @Query(value = "SELECT * FROM recipe r WHERE r.id = ?1", nativeQuery = true)
    RecipeEntity getRecipeById(String recipeId);

    @Query(value = "SELECT * FROM csye7220.recipe order by created_ts desc limit 1;", nativeQuery = true)
    RecipeEntity getLatestRecipe();
}

