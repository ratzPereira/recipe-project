package ratz.springframework.recipeproject.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ratz.springframework.recipeproject.commands.RecipeCommand;
import ratz.springframework.recipeproject.converters.RecipeCommandToRecipe;
import ratz.springframework.recipeproject.converters.RecipeToRecipeCommand;
import ratz.springframework.recipeproject.domain.Recipe;
import ratz.springframework.recipeproject.exceptions.NotFoundException;
import ratz.springframework.recipeproject.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {

        log.debug("Im working!");

        Set<Recipe> recipeSet = new HashSet<>();

        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Integer integer) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(integer);

        if (!recipeOptional.isPresent()) {
            throw new NotFoundException("Recipe Not Found! For id value: " + integer.toString());
        }

        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Integer integer) {
        return recipeToRecipeCommand.convert(findById(integer));
    }


    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeId:" + savedRecipe.getId());

        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    public void deleteById(Integer integer) {
        recipeRepository.deleteById(integer);
    }
}



