package com.darknights.devigation.category.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateCategoryDTO {
    private long id;
    private String name;
    private Long memberId;
}
