package ratz.springframework.recipeproject.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ratz.springframework.recipeproject.domain.UnitOfMeasure;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByUom(){

        Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByUom("Teaspoon");

        assertEquals("Teaspoon", uom.get().getUom());
    }


    //just to see one point, since the spring context is up, this method will take 7ms to test
    @Test
    void findByUomCup(){

        Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByUom("Cup");

        assertEquals("Cup", uom.get().getUom());
    }

}