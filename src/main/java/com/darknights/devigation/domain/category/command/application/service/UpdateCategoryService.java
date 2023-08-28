package com.darknights.devigation.domain.category.command.application.service;

import com.darknights.devigation.domain.category.command.application.dto.UpdateCategoryDTO;
import com.darknights.devigation.domain.category.command.domain.aggregate.entity.Category;
import com.darknights.devigation.domain.category.command.domain.repository.CategoryRepository;
import com.darknights.devigation.domain.category.command.domain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UpdateCategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    @Autowired
    public UpdateCategoryService(CategoryRepository categoryRepository, CategoryService categoryService) {
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @Transactional
    public boolean updateCategory(UpdateCategoryDTO updateCategoryDTO){
        Optional<Category> category = categoryRepository.findById(updateCategoryDTO.getId());
        if(category.isPresent()){
            Category updateCategory = category.get();
            if(!updateCategoryDTO.getName().isEmpty()){
                updateCategory.setName(updateCategoryDTO.getName());
            }
            return true;
        }else{
            return false;
        }
    }
}
