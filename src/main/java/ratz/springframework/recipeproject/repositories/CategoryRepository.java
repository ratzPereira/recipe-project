package ratz.springframework.recipeproject.repositories;

import org.springframework.data.repository.CrudRepository;
import ratz.springframework.recipeproject.domain.Category;


public interface CategoryRepository extends CrudRepository<Category, Integer> {


}
