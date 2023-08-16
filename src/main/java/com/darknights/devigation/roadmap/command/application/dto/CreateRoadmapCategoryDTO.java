package com.darknights.devigation.roadmap.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class CreateRoadmapCategoryDTO {
    private long categoryId;
    private long roadmapId;
    private String position;
    private long childCategoryId;

    public CreateRoadmapCategoryDTO(long categoryId, long roadmapId, String position, long childCategoryId, long parentCategoryId) {
        this.categoryId = categoryId;
        this.roadmapId = roadmapId;
        this.position = position;
        this.childCategoryId = childCategoryId;
        this.parentCategoryId = parentCategoryId;
    }

    private long parentCategoryId;
}
