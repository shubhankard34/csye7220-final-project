package com.csye7220.recipe.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Embeddable
public class Steps implements Comparable<Steps> {

    @NotNull
    @Min(1)
    private int position;

    @NotNull
    private String items;

    public Steps() {
    }

    public Steps(@NotNull @Min(1) int position, @NotNull String items) {
        this.position = position;
        this.items = items;
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

    @Override
    public int compareTo(Steps steps) {
        return this.position - (steps.getPosition());
    }
}
