package com.darknights.devigation.domain.category.command.application.dto;

import com.darknights.devigation.domain.category.command.domain.aggregate.entity.enumtype.Classification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateCategoryDTO {
    private String name;
    private Long memberId;
    private Classification classification;

    public CreateCategoryDTO(String name, Long memberId) {
        this.name = name;
        this.memberId = memberId;
    }
}

