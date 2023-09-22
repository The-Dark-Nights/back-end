package com.darknights.devigation.domain.category.command.domain.service;

import com.darknights.devigation.domain.category.command.application.dto.CreateCategoryDTO;
import com.darknights.devigation.domain.category.command.application.dto.UpdateCategoryDTO;
import com.darknights.devigation.domain.category.command.domain.aggregate.entity.Category;
import com.darknights.devigation.global.common.annotation.DomainService;

@DomainService
public class CategoryService {
    public Category toCategoryEntity(CreateCategoryDTO createCategoryDTO){
        if(createCategoryDTO.getClassification()==null) {
            return new Category(createCategoryDTO.getMemberId(), createCategoryDTO.getName());
        }
        else{
            return new Category(createCategoryDTO.getName(), createCategoryDTO.getMemberId(),createCategoryDTO.getClassification());
        }
    }
    public Category toCategoryEntity(UpdateCategoryDTO updateCategoryDTO){
        return new Category(updateCategoryDTO.getMemberId(),updateCategoryDTO.getMemberId(),updateCategoryDTO.getName());
    }

}
