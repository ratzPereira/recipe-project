package ratz.springframework.recipeproject.services;


import ratz.springframework.recipeproject.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
}
