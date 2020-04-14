package com.geek.example.recipes.service;

import com.geek.example.recipes.command.UnitOfMeasureCommand;
import com.geek.example.recipes.converter.UnitOfMeasureToUnitOfMeasureCommand;
import com.geek.example.recipes.model.UnitOfMeasure;
import com.geek.example.recipes.repository.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UnitOfMeasureServiceImplTest {

    UnitOfMeasureService service;
    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    void testListAllUoms() {
        // given
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId(1L);
        unitOfMeasures.add(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId(2L);
        unitOfMeasures.add(uom2);

        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);

        // when
        Set<UnitOfMeasureCommand> commands = service.listAllUoms();

        // then
        assertEquals(2, commands.size());
        Mockito.verify(unitOfMeasureRepository, Mockito.times(1)).findAll();
    }
}