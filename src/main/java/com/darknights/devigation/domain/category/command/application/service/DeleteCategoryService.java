package com.darknights.devigation.domain.category.command.application.service;

import com.darknights.devigation.domain.category.command.domain.aggregate.entity.Category;
import com.darknights.devigation.domain.category.command.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteCategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public DeleteCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void deleteCategory(long id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            categoryRepository.delete(category.get());
        }else{
            //예외 처리
        }
    }

}
