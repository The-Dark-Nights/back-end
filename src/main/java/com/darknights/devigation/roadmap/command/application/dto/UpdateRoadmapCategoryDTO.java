package com.darknights.devigation.roadmap.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateRoadmapCategoryDTO {
    private long id;
    private long categoryId;
    private long roadmapId;
    private String position;
    private long childCategoryId;
    private long parentCategoryId;
}
