package com.geek.example.recipes.service;

import com.geek.example.recipes.command.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUoms();

}
