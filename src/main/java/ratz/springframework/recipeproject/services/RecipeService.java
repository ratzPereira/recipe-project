package ratz.springframework.recipeproject.services;


import ratz.springframework.recipeproject.commands.RecipeCommand;
import ratz.springframework.recipeproject.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Integer integer);

    RecipeCommand findCommandById(Integer integer);

    RecipeCommand saveRecipeCommand(RecipeCommand command);
}
