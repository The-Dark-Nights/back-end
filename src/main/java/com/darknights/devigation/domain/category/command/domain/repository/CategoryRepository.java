package com.darknights.devigation.domain.category.command.domain.repository;

import com.darknights.devigation.domain.category.command.domain.aggregate.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
