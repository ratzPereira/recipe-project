package ratz.springframework.recipeproject.repositories;


import org.springframework.data.repository.CrudRepository;
import ratz.springframework.recipeproject.domain.Recipe;


public interface RecipeRepository extends CrudRepository<Recipe, Integer> {


}
