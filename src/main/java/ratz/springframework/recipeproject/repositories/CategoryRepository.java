package ratz.springframework.recipeproject.repositories;

import org.springframework.data.repository.CrudRepository;
import ratz.springframework.recipeproject.domain.Category;

import java.util.Optional;


public interface CategoryRepository extends CrudRepository<Category, Integer> {

    Optional<Category> findByDescription(String description);

}
