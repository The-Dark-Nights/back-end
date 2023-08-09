package com.darknights.devigation.category.command.application.service;


import com.darknights.devigation.category.command.application.dto.CreateCategoryDTO;
import com.darknights.devigation.category.command.domain.aggregate.entity.Category;
import com.darknights.devigation.category.command.domain.repository.CategoryRepository;
import com.darknights.devigation.category.command.domain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;
    @Autowired
    public CreateCategoryService(CategoryRepository categoryRepository, CategoryService categoryService) {
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    public Category createCategory(CreateCategoryDTO createCategoryDTO){
        return categoryRepository.save(categoryService.toCategoryEntity(createCategoryDTO));
    }
}
