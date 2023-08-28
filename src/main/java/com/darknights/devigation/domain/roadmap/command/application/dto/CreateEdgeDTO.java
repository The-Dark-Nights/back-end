package com.darknights.devigation.domain.roadmap.command.application.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class CreateEdgeDTO {
    private long roadmapId;
    private String edgeId;
    private long sourceCategory;
    private long targetCategory;
}
