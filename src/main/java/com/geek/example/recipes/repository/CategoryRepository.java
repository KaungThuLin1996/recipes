package com.geek.example.recipes.repository;

import com.geek.example.recipes.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
