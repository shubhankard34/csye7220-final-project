package com.csye7220.recipe.model.wrapper;

import com.csye7220.recipe.model.Steps;

public class StepsWrapper {
	private int position;

	private String items;

	public StepsWrapper(Steps steps) {
		this.position = steps.getPosition();
		this.items = steps.getItems();
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

}
