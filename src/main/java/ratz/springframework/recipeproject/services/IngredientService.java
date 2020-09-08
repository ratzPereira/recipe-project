package ratz.springframework.recipeproject.services;

import ratz.springframework.recipeproject.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Integer recipeId, Integer ingredientId);

}
