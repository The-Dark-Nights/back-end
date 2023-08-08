package com.darknights.devigation.category.command.application.service;

import com.darknights.devigation.category.command.application.dto.UpdateCategoryDTO;
import com.darknights.devigation.category.command.domain.aggregate.entity.Category;
import com.darknights.devigation.category.command.domain.repository.CategoryRepository;
import com.darknights.devigation.category.command.domain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    @Autowired
    public UpdateCategoryService(CategoryRepository categoryRepository, CategoryService categoryService) {
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    public Category updateCategory(UpdateCategoryDTO updateCategoryDTO){
        return categoryRepository.save(categoryService.toCategoryEntity(updateCategoryDTO));
    }
}
