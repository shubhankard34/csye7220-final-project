package com.csye7220.recipe.model.wrapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.csye7220.recipe.model.RecipeEntity;
import com.csye7220.recipe.model.Steps;

public class RecipeWrapper {
	private String id;

	private Date created_ts;

	private Date updated_ts;

	private String userFirstName;

	private String userLastName;

	private int cook_time_in_min;

	private int prep_time_in_min;

	private int total_time_in_min;

	private String title;

	private String cuisine;

	private int servings;

	private List<String> ingredients;

	private List<StepsWrapper> steps;

	private NutritionInformationWrapper nutrition_information;

	public RecipeWrapper(RecipeEntity re) {
		this.id = re.getId();
		this.created_ts = re.getCreated_ts();
		this.updated_ts = re.getUpdated_ts();
		this.userFirstName = re.getUserFirstName();
		this.userLastName = re.getUserLastName();
		this.cook_time_in_min = re.getCook_time_in_min();
		this.prep_time_in_min = re.getPrep_time_in_min();
		this.total_time_in_min = re.getTotal_time_in_min();
		this.title = re.getTitle();
		this.cuisine = re.getCuisine();
		this.servings = re.getServings();
		this.nutrition_information = new NutritionInformationWrapper(re.getNutrition_information());
		this.ingredients = new ArrayList<>();
		for (String ing : re.getIngredients()) {
			this.ingredients.add(ing);
		}
		this.steps = new ArrayList<>();
		for (Steps step : re.getSteps()) {
			this.steps.add(new StepsWrapper(step));
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreated_ts() {
		return created_ts;
	}

	public void setCreated_ts(Date created_ts) {
		this.created_ts = created_ts;
	}

	public Date getUpdated_ts() {
		return updated_ts;
	}

	public void setUpdated_ts(Date updated_ts) {
		this.updated_ts = updated_ts;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public int getCook_time_in_min() {
		return cook_time_in_min;
	}

	public void setCook_time_in_min(int cook_time_in_min) {
		this.cook_time_in_min = cook_time_in_min;
	}

	public int getPrep_time_in_min() {
		return prep_time_in_min;
	}

	public void setPrep_time_in_min(int prep_time_in_min) {
		this.prep_time_in_min = prep_time_in_min;
	}

	public int getTotal_time_in_min() {
		return total_time_in_min;
	}

	public void setTotal_time_in_min(int total_time_in_min) {
		this.total_time_in_min = total_time_in_min;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCuisine() {
		return cuisine;
	}

	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}

	public int getServings() {
		return servings;
	}

	public void setServings(int servings) {
		this.servings = servings;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	public List<StepsWrapper> getSteps() {
		return steps;
	}

	public void setSteps(List<StepsWrapper> steps) {
		this.steps = steps;
	}

	public NutritionInformationWrapper getNutrition_information() {
		return nutrition_information;
	}

	public void setNutrition_information(NutritionInformationWrapper nutrition_information) {
		this.nutrition_information = nutrition_information;
	}

}
