package com.darknights.devigation.category.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateCategoryDTO {
    private String name;
    private Long memberId;
}

