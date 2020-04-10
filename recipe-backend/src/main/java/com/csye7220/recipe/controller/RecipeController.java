package com.csye7220.recipe.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.csye7220.recipe.model.RecipeEntity;
import com.csye7220.recipe.model.wrapper.RecipeWrapper;
import com.csye7220.recipe.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.micrometer.core.instrument.MeterRegistry;

@Controller
@RequestMapping(path = "/v1")
public class RecipeController {

	@Autowired
	RecipeService recipeService;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	MeterRegistry registry;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private static Comparator<RecipeWrapper> recipeWrapperComparator = new Comparator<RecipeWrapper>() {
		@Override
		public int compare(RecipeWrapper t1, RecipeWrapper t2) {
			Date date1 = t1.getUpdated_ts();
			Date date2 = t2.getUpdated_ts();
			if (date1.compareTo(date2) > 0) {
				return -1;
			} else if (date1.compareTo(date2) < 0) {
				return 1;
			}
			return 0;
		}
	};

	/* to save an Recipe */
	@PostMapping("/recipe")
	public ResponseEntity<Object> createRecipe(@Valid @RequestBody RecipeEntity recipe) {
		registry.counter("custom.metrics.counter", "ApiCall", "RecipePost").increment();
		log.info("Inside post /recipe mapping");
		HashMap<String, Object> entities = new HashMap<String, Object>();

		recipe.setCreated_ts(new Date());
		recipe.setUpdated_ts(new Date());
		recipe.getNutrition_information().setRecipe(recipe);
		if (recipe.getCook_time_in_min() % 5 > 0 && recipe.getTotal_time_in_min() % 5 > 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		recipe.setTotal_time_in_min(recipe.getCook_time_in_min() + recipe.getPrep_time_in_min());
		RecipeEntity savedRecipe;
		try {
			savedRecipe = recipeService.saveRecipe(recipe);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(new RecipeWrapper(savedRecipe), HttpStatus.CREATED);
	}

	@GetMapping("/recipe/{id}")
	public ResponseEntity<Object> getRecipeById(@PathVariable(value = "id") String recipeId) {
		registry.counter("custom.metrics.counter", "ApiCall", "ReipeGet").increment();
		log.info("Inside get /recipe mapping");
		HashMap<String, Object> entities = new HashMap<String, Object>();
		RecipeEntity recipeEntity = recipeService.getRecipeById(recipeId);
		if (null == recipeEntity) {
			entities.put("message", "Recipe does not exists");
			return new ResponseEntity<>(entities, HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok(new RecipeWrapper(recipeEntity));
		}
	}

	public List<RecipeWrapper> retrieveAllRecipes() {
		List<RecipeWrapper> recipeWrappers = new ArrayList<>();
		for (RecipeEntity rec : recipeService.getAllRecipes()) {
			recipeWrappers.add(new RecipeWrapper(rec));
		}
		recipeWrappers.sort(recipeWrapperComparator);
		log.info("Inside get /recipe mapping");
		return recipeWrappers;
	}

	@GetMapping("/allRecipes")
	public ResponseEntity<Object> getAllRecipes() {
		registry.counter("custom.metrics.counter", "ApiCall", "RecipeAll").increment();
		return ResponseEntity.ok(retrieveAllRecipes());

	}

	@GetMapping("/recipes")
	public ResponseEntity<Object> getLatestRecipe() {
		registry.counter("custom.metrics.counter", "ApiCall", "RecipeLatest").increment();
		log.info("Inside get /recipes mapping");
		HashMap<String, Object> entities = new HashMap<String, Object>();
		RecipeEntity recipeEntity = recipeService.getLatestRecipe();
		if (null == recipeEntity) {
			entities.put("message", "No Recipe Exists");
			return new ResponseEntity<Object>(entities, HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(new RecipeWrapper(recipeEntity));

	}

	@DeleteMapping("/recipe/{id}")
	public ResponseEntity<Object> deleteRecipeById(@PathVariable(value = "id") String recipeId) throws IOException {
		registry.counter("custom.metrics.counter", "ApiCall", "RecipeDelete").increment();
		log.info("Inside delete /recipe mapping");
		HashMap<String, Object> entities = new HashMap<String, Object>();
		RecipeEntity recipeEntity = recipeService.getRecipeById(recipeId);
		if (null == recipeEntity) {
			entities.put("message", "Recipe does not exists");
			return new ResponseEntity<>(entities, HttpStatus.NOT_FOUND);
		} else {

			recipeService.deleteRecipeById(recipeEntity);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	@PutMapping("/recipe/{id}")
	public ResponseEntity<Object> updateRecipe(@PathVariable(value = "id") String recipeId,
			@Valid @RequestBody RecipeEntity recipe) {
		registry.counter("custom.metrics.counter", "ApiCall", "RecipePut").increment();
		log.info("Inside Update /recipe mapping");
		HashMap<String, Object> entities = new HashMap<String, Object>();
		RecipeEntity recipeEntity = recipeService.getRecipeById(recipeId);
		if (null == recipeEntity) {
			entities.put("message", "Recipe does not exists");
			return new ResponseEntity<>(entities, HttpStatus.NOT_FOUND);
		} else {
			try {
				recipe.setId(recipeEntity.getId());
				recipe.setUpdated_ts(new Date());
				recipe.setCreated_ts(recipeEntity.getCreated_ts());
				recipe.getNutrition_information().setRecipe(recipe);

				RecipeEntity savedRecipe = recipeService.saveRecipe(recipe);

				return new ResponseEntity<>(new RecipeWrapper(savedRecipe), HttpStatus.OK);

			} catch (Exception e) {
				log.error(e.getMessage());
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}

	}

	@GetMapping("/health")
	public ResponseEntity<Object> getHealth() {
		String test = "Health is ok!!!";
		return ResponseEntity.ok(test);
	}

}
