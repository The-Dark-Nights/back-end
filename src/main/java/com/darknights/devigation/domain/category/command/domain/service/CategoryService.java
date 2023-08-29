package com.darknights.devigation.domain.category.command.domain.service;

import com.darknights.devigation.domain.category.command.application.dto.CreateCategoryDTO;
import com.darknights.devigation.domain.category.command.application.dto.UpdateCategoryDTO;
import com.darknights.devigation.domain.category.command.domain.aggregate.entity.Category;
import com.darknights.devigation.global.common.annotation.DomainService;

@DomainService
public class CategoryService {
    public Category toCategoryEntity(CreateCategoryDTO createCategoryDTO){
       return new Category(createCategoryDTO.getMemberId(),createCategoryDTO.getName());
    }
    public Category toCategoryEntity(UpdateCategoryDTO updateCategoryDTO){
        return new Category(updateCategoryDTO.getMemberId(),updateCategoryDTO.getMemberId(),updateCategoryDTO.getName());
    }

}
