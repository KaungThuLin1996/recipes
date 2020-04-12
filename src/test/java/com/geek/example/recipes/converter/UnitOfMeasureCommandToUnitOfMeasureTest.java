package com.geek.example.recipes.converter;

import com.geek.example.recipes.command.UnitOfMeasureCommand;
import com.geek.example.recipes.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureCommandToUnitOfMeasureTest {

    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTION = "description";

    private UnitOfMeasureCommandToUnitOfMeasure converter;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    void convert() {
        // given
        UnitOfMeasureCommand uomc = new UnitOfMeasureCommand();
        uomc.setId(ID_VALUE);
        uomc.setDescription(DESCRIPTION);

        // when
        UnitOfMeasure uom = converter.convert(uomc);

        // then
        assertEquals(ID_VALUE, uom.getId());
        assertEquals(DESCRIPTION, uom.getDescription());
    }
}