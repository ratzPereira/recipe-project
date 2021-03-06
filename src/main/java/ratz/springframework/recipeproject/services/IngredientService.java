package ratz.springframework.recipeproject.services;

import ratz.springframework.recipeproject.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Integer recipeId, Integer ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteById(Integer recipeId, Integer idToDelete);
}
