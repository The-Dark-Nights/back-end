package com.darknights.devigation.category.command.domain.service;

import com.darknights.devigation.category.command.application.dto.CreateCategoryDTO;
import com.darknights.devigation.category.command.application.dto.UpdateCategoryDTO;
import com.darknights.devigation.category.command.domain.aggregate.entity.Category;
import com.darknights.devigation.category.command.domain.repository.CategoryRepository;
import com.darknights.devigation.common.annotation.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@DomainService
public class CategoryService {
    public Category toCategoryEntity(CreateCategoryDTO createCategoryDTO){
       return new Category(createCategoryDTO.getMemberId(),createCategoryDTO.getName());
    }
    public Category toCategoryEntity(UpdateCategoryDTO updateCategoryDTO){
        return new Category(updateCategoryDTO.getMemberId(),updateCategoryDTO.getMemberId(),updateCategoryDTO.getName());
    }

}
