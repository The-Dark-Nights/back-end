package com.darknights.devigation.roadmap.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SaveNodeDTO {
    private long roadmapId;
    private long categoryId;
    private String position;
}
