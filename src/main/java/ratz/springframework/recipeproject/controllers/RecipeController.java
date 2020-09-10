package ratz.springframework.recipeproject.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ratz.springframework.recipeproject.commands.RecipeCommand;
import ratz.springframework.recipeproject.exceptions.NotFoundException;
import ratz.springframework.recipeproject.services.RecipeService;

import javax.validation.Valid;

@Slf4j
@Controller
public class RecipeController {

    private static final String RECIPE_RECIPEFORM_URL = "recipe/recipeform";

    @Valid
    public final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model){

        model.addAttribute("recipe", recipeService.findById(new Integer(id)));

        return "recipe/show";

    }


    @GetMapping
    @RequestMapping("recipe/new")
    public String newRecipe (Model model){

        model.addAttribute("recipe" , new RecipeCommand());

        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrUpdate( @ModelAttribute("recipe")@Valid RecipeCommand command, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println("im here");
            bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.toString()));

            System.out.println("im here");
            return "recipe/recipeform";
        }
        System.out.println("Nao entrei no IF");
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){

        model.addAttribute("recipe", recipeService.findCommandById(Integer.valueOf(id)));

        return "recipe/recipeform";
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/delete")
    public String deleteById(@PathVariable String id){

        log.debug("Deleting id: " + id);

        recipeService.deleteById(Integer.valueOf(id));
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exeption){

        log.error("Handling not found exception");
        log.error(exeption.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");
        modelAndView.addObject("exception" , exeption);


        return modelAndView;
    }

}


