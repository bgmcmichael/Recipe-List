package com.practice.SpringRecipeList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by fenji on 2/21/2018.
 */
public interface RecipeRepo extends CrudRepository<Recipe, Integer> {
    List<Recipe> findByStyle(String style);

    @Query("SELECT g FROM Recipe g WHERE g.name LIKE ?1%")
    List<Recipe> findByNameStartsWith(String name);


}

