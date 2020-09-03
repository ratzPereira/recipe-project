package ratz.springframework.recipeproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ratz.springframework.recipeproject.domain.Category;
import ratz.springframework.recipeproject.domain.UnitOfMeasure;
import ratz.springframework.recipeproject.repositories.CategoryRepository;
import ratz.springframework.recipeproject.repositories.UnitOfMeasureRepository;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "index"})
    public String getIndexPage() {

        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByUom("Teaspoon");

        System.out.println("Cat Id is : " + categoryOptional.get().getId());
        System.out.println("UOM ID is : " + unitOfMeasureOptional.get().getId());

        return "index";
    }

}
