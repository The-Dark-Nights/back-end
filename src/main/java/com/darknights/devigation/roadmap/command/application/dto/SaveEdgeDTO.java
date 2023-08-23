package com.darknights.devigation.roadmap.command.application.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SaveEdgeDTO {
    private long roadmapId;
    private String EdgeId;
    private long sourceCategory;
    private long targetCategory;
}
