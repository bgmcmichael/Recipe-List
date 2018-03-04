package com.practice.SpringRecipeList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fenji on 2/21/2018.
 */
@Controller
public class SpringRecipeListController {
    @Autowired
    RecipeRepo recipes;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, String style) {
        List<Recipe> recipeList = new ArrayList<Recipe>();
        if (style != null) {
            recipeList = recipes.findByStyle(style);
        } else {
            Iterable<Recipe> allRecipes = recipes.findAll();
            for (Recipe recipe : allRecipes) {
                recipeList.add(recipe);
            }
        }
        model.addAttribute("recipes", recipeList);
        return "home";
    }

    @RequestMapping(path = "/recipes", method = RequestMethod.GET)
    public String games(Model model, HttpSession session) {
        return "recipes";
    }

    @RequestMapping(path = "/add-recipe", method = RequestMethod.POST)
    public String addRecipe(String name, String style, String recipe) {
        Recipe recipe1 = new Recipe(name, style, recipe);
        recipes.save(recipe1);
        return "redirect:/";
    }


    @RequestMapping(path = "/searchByName", method = RequestMethod.GET)
    public String queryRecipesByName(Model model, String search) {
        System.out.println("Searching by ..." + search);
        List<Recipe> recipeList = recipes.findByNameStartsWith(search);
        model.addAttribute("recipes", recipeList);
        return "home";
    }

    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    public String deleteRecipe(Model model, Integer recipeID) {
        if (recipeID != null) {
            recipes.delete(recipeID);
        }

        return "redirect:/";
    }

    @RequestMapping(path = "/modify", method = RequestMethod.GET)
    public String modify(Model model, Integer recipeID) {
        if (recipeID != null) {
            Recipe recipe = recipes.findOne(recipeID);
            recipe.name = " ** " + recipe.name;
            recipes.save(recipe);
        }

        return "redirect:/";
    }
}
