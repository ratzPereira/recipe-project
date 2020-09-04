package ratz.springframework.recipeproject.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import ratz.springframework.recipeproject.domain.Recipe;
import ratz.springframework.recipeproject.services.RecipeService;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


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
    void testMockMVC() throws Exception {

        //we create a servlet mock context to test MVC controllers
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void getIndexPage() {

        //given
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());

        Recipe recipe = new Recipe();
        recipe.setId(1);

        recipes.add(recipe);

        when(recipeService.getRecipes()).thenReturn(recipes);

        //é para usar em vez do anySet, assim temos a confirmaçao do que vem dentro do set é um recipe
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String viewName = indexController.getIndexPage(model);

        //then
        assertEquals("index" , viewName);
        verify(recipeService, times(1)).getRecipes();

        //eq means equal, if the first equals recipes
        // on source code -> model.addAttribute("recipes", recipeService.getRecipes());
        verify(model,times(1)).addAttribute(eq("recipes") , argumentCaptor.capture());

        //confirmação se vem 2 recipes no set
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2,setInController.size());

    }
}