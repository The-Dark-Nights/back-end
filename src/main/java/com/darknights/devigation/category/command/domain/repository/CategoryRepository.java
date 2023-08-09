package com.darknights.devigation.category.command.domain.repository;

import com.darknights.devigation.category.command.domain.aggregate.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
