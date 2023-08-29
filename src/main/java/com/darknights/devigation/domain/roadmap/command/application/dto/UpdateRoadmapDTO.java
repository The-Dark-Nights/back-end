package com.darknights.devigation.domain.roadmap.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateRoadmapDTO {
    private Long id;
    private String title;
    private Long memberId;
}
