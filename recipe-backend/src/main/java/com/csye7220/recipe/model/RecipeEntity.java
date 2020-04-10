package com.csye7220.recipe.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "recipe")
@EntityListeners(AuditingEntityListener.class)

public class RecipeEntity {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date created_ts;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updated_ts;

	@Column(nullable = false)
	@NotNull
	private String userFirstName;

	@Column(nullable = false)
	@NotNull
	private String userLastName;

	@Column(nullable = false)
	@NotNull
	private int cook_time_in_min;

	@Column(nullable = false)
	private int prep_time_in_min;

	@Column
	private int total_time_in_min;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String cuisine;

	@Column(nullable = false)
	@NotNull
	@Range(min = 1, max = 5)
	private int servings;

	@ElementCollection
	@UniqueElements
	private List<String> ingredients = new ArrayList<>();

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "steps", joinColumns = @JoinColumn(name = "id"))
	@OrderBy("position ASC")
	private SortedSet<Steps> steps = new TreeSet<>();

	@OneToOne(mappedBy = "recipe", cascade = CascadeType.ALL)
	private NutritionInformation nutrition_information;

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

	public int getPrep_time_in_min() {
		return prep_time_in_min;
	}

	public void setPrep_time_in_min(int prep_time_in_min) {
		this.prep_time_in_min = prep_time_in_min;
	}

	public void setCook_time_in_min(int cook_time_in_min) {
		this.cook_time_in_min = cook_time_in_min;
	}

	public int getTotal_time_in_min() {
		return total_time_in_min;
	}

	public void setTotal_time_in_min(int total_time_in_min) {
		this.total_time_in_min = this.cook_time_in_min + this.prep_time_in_min;
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

	public SortedSet<Steps> getSteps() {
		return steps;
	}

	public void setSteps(SortedSet<Steps> steps) {
		this.steps = steps;
	}

	public NutritionInformation getNutrition_information() {
		return nutrition_information;
	}

	public void setNutrition_information(NutritionInformation nutrition_information) {
		this.nutrition_information = nutrition_information;
	}
}
