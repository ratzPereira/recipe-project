package ratz.springframework.recipeproject.bootstrap;


import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ratz.springframework.recipeproject.domain.*;
import ratz.springframework.recipeproject.repositories.CategoryRepository;
import ratz.springframework.recipeproject.repositories.RecipeRepository;
import ratz.springframework.recipeproject.repositories.UnitOfMeasureRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;


    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        recipeRepository.saveAll(getRecipes());

    }


    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>(2);

        //get uom
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByUom("Each");

        if (!unitOfMeasureOptional.isPresent()) {
            throw new RuntimeException("Uom not found");
        }

        Optional<UnitOfMeasure> unitOfMeasureTablespoon = unitOfMeasureRepository.findByUom("Tablespoon");

        if (!unitOfMeasureTablespoon.isPresent()) {
            throw new RuntimeException("Uom not found");
        }

        Optional<UnitOfMeasure> unitOfMeasureTeaspoon = unitOfMeasureRepository.findByUom("Teaspoon");

        if (!unitOfMeasureTeaspoon.isPresent()) {
            throw new RuntimeException("Uom not found");
        }

        Optional<UnitOfMeasure> unitOfMeasureDash = unitOfMeasureRepository.findByUom("Dash");

        if (!unitOfMeasureDash.isPresent()) {
            throw new RuntimeException("Uom not found");
        }

        Optional<UnitOfMeasure> unitOfMeasurePint = unitOfMeasureRepository.findByUom("Pinch");

        if (!unitOfMeasurePint.isPresent()) {
            throw new RuntimeException("Uom not found");
        }

        Optional<UnitOfMeasure> unitOfMeasurecup = unitOfMeasureRepository.findByUom("Cup");

        if (!unitOfMeasurecup.isPresent()) {
            throw new RuntimeException("Uom not found");
        }

        //get optionals
        UnitOfMeasure eachUom = unitOfMeasureOptional.get();
        UnitOfMeasure tableSpoon = unitOfMeasureTablespoon.get();
        UnitOfMeasure teaspoon = unitOfMeasureTeaspoon.get();
        UnitOfMeasure dash = unitOfMeasureDash.get();
        UnitOfMeasure pint = unitOfMeasurePint.get();
        UnitOfMeasure cups = unitOfMeasurecup.get();

        //get categories
        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if (!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Category not found");
        }

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if (!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Category not found");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();


        //yummy guac ( recipes)
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setCookTime(0);
        guacRecipe.setPrepTime(10);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");


        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("HOW TO STORE GUACAMOLE\n" +
                "Guacamole is best eaten right after it’s made. Like apples, avocados start to oxidize and turn brown once they’ve been cut. That said, the acid in the lime juice you add to guacamole can help slow down that process, and if you store the guacamole properly, you can easily make it a few hours ahead if you are preparing for a party.\n" +
                "\n" +
                "The trick to keeping guacamole green is to make sure air doesn’t touch it! Transfer it to a container, cover with plastic wrap, and press down on the plastic wrap to squeeze out any air pockets. Make sure any exposed surface of the guacamole is touching the plastic wrap, not air. This will keep the amount of browning to a minimum.\n" +
                "\n" +
                "You can store the guacamole in the fridge this way for up to three days.\n" +
                "\n" +
                "If you leave the guacamole exposed to air, it will start to brown and discolor. That browning isn’t very appetizing, but the guacamole is still good. You can either scrape off the brown parts and discard, or stir them into the rest of the guacamole.");

        guacNotes.setRecipe(guacRecipe);
        guacRecipe.setNotes(guacNotes);


        guacRecipe.getIngredients().add(new Ingredient("Ripe Avocados", new BigDecimal(2), eachUom, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("Kosher Salt", new BigDecimal(5), teaspoon, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("Fresh lime juice, or lemon juice", new BigDecimal(2), tableSpoon, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("Minced red onion", new BigDecimal(2), tableSpoon, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("Serrano chiles", new BigDecimal(2), eachUom, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("Cilantro", new BigDecimal(2), tableSpoon, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("Black pepper", new BigDecimal(2), dash, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("Ripe tomato, chopped", new BigDecimal(5), eachUom, guacRecipe));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        //add to return list
        recipes.add(guacRecipe);


        //yummi tacos
        Recipe tacoRecipe = new Recipe();
        tacoRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacoRecipe.setCookTime(9);
        tacoRecipe.setPrepTime(20);
        tacoRecipe.setDifficulty(Difficulty.MODERATE);

        tacoRecipe.setDirections("1 Coat chicken with rub, place in slow cooker: Whisk the rub ingredients together in a bowl. Dredge the boneless skinless chicken thighs in the rub. Place the chicken thighs in a slow cooker.\n" +
                "\n" +
                "2 Add pineapple juice, tomato paste: Pour the pineapple juice over the chicken. Add the tomato paste to the juice.\n" +
                "\n" +
                "3 Cover and slow cook: Cover and cook in the slow cooker for 3 hours on high setting or 6 hours on low.\n" +
                "\n" +
                "4 Shred chicken meat with forks: When the chicken is done, it should be fork tender. Use two forks to shred the meat completely in the slow cooker bowl.\n" +
                "\n" +
                "5 Serve: Use the taco meat with heated and softened corn tortillas, sprinkle with lime juice, adding garnishes of finely shredded cabbage, thinly sliced radishes, avocado, and cilantro.");


        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("STORING AND FREEZING SHREDDED CHICKEN TACO MEAT\n" +
                "The shredded chicken keeps well in the fridge for up to five days and can either be reheated slowly in the slow cooker, over low heat on the stovetop, or in the microwave.\n" +
                "\n" +
                "This chicken also freezes well. Package in freezer bags or other storage containers with as much air pressed out as possible to prevent freezer burn, and freeze for up to three months.");

        tacoRecipe.setNotes(tacoNotes);
        tacoNotes.setRecipe(tacoRecipe);

        tacoRecipe.getIngredients().add(new Ingredient("Ancho chili powder", new BigDecimal(2), tableSpoon, tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("Dried oregano", new BigDecimal(1), teaspoon, tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("Dried cumin", new BigDecimal(1), teaspoon, tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("Sugar", new BigDecimal(1), teaspoon, tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("Salt", new BigDecimal(5), teaspoon, tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("Clove of Garlic", new BigDecimal(1), eachUom, tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("Finely grated orange zestr", new BigDecimal(1), tableSpoon, tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("Olive oil", new BigDecimal(2), tableSpoon, tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("Boneless chicken thighs", new BigDecimal(4), tableSpoon, tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("Small corn tortillas", new BigDecimal(8), eachUom, tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("Packed baby arugula", new BigDecimal(3), cups, tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("Medium ripe avocados", new BigDecimal(2), eachUom, tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("Radishes,thinly sliced", new BigDecimal(4), eachUom, tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("cherry tomatoes,halved", new BigDecimal(.5), pint, tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("Red onion", new BigDecimal(.25), eachUom, tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("Cilantro, roughly chopped", new BigDecimal(4), eachUom, tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("Cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cups, tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("Lime, cut into wedges", new BigDecimal(4), cups, tacoRecipe));


        tacoRecipe.getCategories().add(americanCategory);
        tacoRecipe.getCategories().add(mexicanCategory);


        recipes.add(tacoRecipe);
        return recipes;

    }

}
