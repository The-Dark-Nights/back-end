package com.darknights.devigation.roadmap.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class CreateNodeDTO {
    private long roadmapId;
    private long categoryId;
    private String position;
}
