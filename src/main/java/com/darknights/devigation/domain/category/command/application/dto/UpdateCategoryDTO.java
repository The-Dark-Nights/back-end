package com.darknights.devigation.domain.category.command.application.dto;

import com.darknights.devigation.domain.category.command.domain.aggregate.entity.enumtype.Classification;
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
    private Classification classification;

    public UpdateCategoryDTO(long id, String name, Long memberId) {
        this.id = id;
        this.name = name;
        this.memberId = memberId;
    }
}
