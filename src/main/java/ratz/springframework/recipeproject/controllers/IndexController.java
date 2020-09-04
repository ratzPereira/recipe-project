package ratz.springframework.recipeproject.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ratz.springframework.recipeproject.services.RecipeService;

@Slf4j
@Controller
public class IndexController {


    // OLD EXAMPLE WITHOUT RECIPEBOOTSTRAP

    /* private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    } */

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "index"})
    public String getIndexPage(Model model) {


        // OLD EXAMPLE WITHOUT RECIPEBOOTSTRAP

        /*Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByUom("Teaspoon");

        System.out.println("Cat Id is : " + categoryOptional.get().getId());
        System.out.println("UOM ID is : " + unitOfMeasureOptional.get().getId());*/

        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }

}
