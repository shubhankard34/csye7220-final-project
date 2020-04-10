package com.csye7220.recipe.model.wrapper;

import com.csye7220.recipe.model.NutritionInformation;

public class NutritionInformationWrapper {
	private String id;

	private int calories;

	private float cholesterol_in_mg;

	private int sodium_in_mg;

	private float carbohydrates_in_grams;

	private float protein_in_grams;

	public NutritionInformationWrapper(NutritionInformation ni) {
		this.id = ni.getId();
		this.calories = ni.getCalories();
		this.cholesterol_in_mg = ni.getCholesterol_in_mg();
		this.sodium_in_mg = ni.getSodium_in_mg();
		this.carbohydrates_in_grams = ni.getCarbohydrates_in_grams();
		this.protein_in_grams = ni.getProtein_in_grams();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
