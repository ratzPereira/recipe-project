package ratz.springframework.recipeproject.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import ratz.springframework.recipeproject.services.RecipeService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class IndexControllerTest {

    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    IndexController indexController;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);

        //inicializamos o controller
        indexController = new IndexController(recipeService);
    }

    @Test
    void getIndexPage() {

        String viewName = indexController.getIndexPage(model);
        assertEquals("index" , viewName);
        verify(recipeService, times(1)).getRecipes();

        //eq means equal, if the first equals recipes
        // on source code -> model.addAttribute("recipes", recipeService.getRecipes());
        verify(model,times(1)).addAttribute(eq("recipes") , anySet());


    }
}