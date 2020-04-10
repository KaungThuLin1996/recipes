package com.geek.example.recipes.repository;

import com.geek.example.recipes.model.UnitOfMeasure;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UnitOfMeasureRepositoryIT {

    @Autowired
    private UnitOfMeasureRepository repository;

    @Test
    void findByDescriptionTeaspoon() {
        Optional<UnitOfMeasure> uomOptional = repository.findByDescription("Teaspoon");
        assertEquals("Teaspoon", uomOptional.get().getDescription());
    }

    @Test
    void findByDescriptionCup() {
        Optional<UnitOfMeasure> uomOptional = repository.findByDescription("Cup");
        assertEquals("Cup", uomOptional.get().getDescription());
    }
}