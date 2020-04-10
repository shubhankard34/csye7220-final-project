package com.csye7220.recipe.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class NutritionInformation {

    @Id
    @Column(name = "id")
    private String id;

    @OneToOne
    @MapsId
    private RecipeEntity recipe;

    @NotNull
    private int calories;

    @NotNull
    private float cholesterol_in_mg;

    @NotNull
    private int sodium_in_mg;

    @NotNull
    private float carbohydrates_in_grams;

    @NotNull
    private float protein_in_grams;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RecipeEntity getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeEntity recipe) {
        this.recipe = recipe;
        this.id = recipe.getId();
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public float getCholesterol_in_mg() {
        return cholesterol_in_mg;
    }

    public void setCholesterol_in_mg(float cholesterol_in_mg) {
        this.cholesterol_in_mg = cholesterol_in_mg;
    }

    public int getSodium_in_mg() {
        return sodium_in_mg;
    }

    public void setSodium_in_mg(int sodium_in_mg) {
        this.sodium_in_mg = sodium_in_mg;
    }

    public float getCarbohydrates_in_grams() {
        return carbohydrates_in_grams;
    }

    public void setCarbohydrates_in_grams(float carbohydrates_in_grams) {
        this.carbohydrates_in_grams = carbohydrates_in_grams;
    }

    public float getProtein_in_grams() {
        return protein_in_grams;
    }

    public void setProtein_in_grams(float protein_in_grams) {
        this.protein_in_grams = protein_in_grams;
    }
}

