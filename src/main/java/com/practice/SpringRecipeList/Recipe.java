package com.practice.SpringRecipeList;

import javax.persistence.*;

/**
 * Created by fenji on 2/21/2018.
 */
@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String style;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    @Column(nullable = false)
    String recipe;

    public Recipe() {
    }

    public Recipe(String name, String style, String recipe) {
        this.name = name;
        this.style = style;
        this.recipe = recipe;
    }
}
