package com.practice.SpringRecipeList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fenji on 9/19/2016.
 */
@RestController
public class SpringRecipeListJSONController {
    @Autowired
    RecipeRepo recipes;

    ArrayList<Recipe> getAllRecipes() {
        ArrayList<Recipe> recipesList = new ArrayList<Recipe>();
        Iterable<Recipe> allRecipes = recipes.findAll();
        for (Recipe recipes : allRecipes) {
            recipesList.add(recipes);
        }

        return recipesList;
    }

    @RequestMapping(path = "/recipes.json", method = RequestMethod.GET)
    public ArrayList<Recipe> getRecipes(){
        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
        Iterable<Recipe> allRecipes = recipes.findAll();
        for (Recipe recipe : allRecipes) {
            recipeList.add(recipe);
            System.out.println(recipe.name);
        }

        return recipeList;
    }

    @RequestMapping(path = "/deleteRecipe.json", method = RequestMethod.GET)
    public List<Recipe> deleteRecipe(int recipeID) {
        System.out.println("deleting recipe with ID " + recipeID);
        recipes.delete(recipeID);

        return getRecipes();
    }

    @RequestMapping(path = "/addRecipe.json", method = RequestMethod.POST)
    public List<Recipe> addRecipe(HttpSession session, @RequestBody Recipe newRecipe) throws Exception {
    System.out.println(newRecipe.getName() + "attempting to save");
        recipes.save(newRecipe);

        return getRecipes();
    }

    @RequestMapping(path = "/delete.json", method = RequestMethod.GET)
    public List<Recipe> deleteRecipe(HttpSession session, @RequestBody Recipe recipe) {
        recipes.delete(recipe.getId());

        return getRecipes();
    }
}