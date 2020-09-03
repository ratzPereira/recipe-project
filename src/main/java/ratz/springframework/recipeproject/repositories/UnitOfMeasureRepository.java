package ratz.springframework.recipeproject.repositories;

import org.springframework.data.repository.CrudRepository;
import ratz.springframework.recipeproject.domain.UnitOfMeasure;

import java.util.Optional;


public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Integer> {

    Optional<UnitOfMeasure> findByUom (String description);
}
