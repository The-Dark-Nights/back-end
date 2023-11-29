package com.darknights.devigation.domain.roadmap.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateRoadmapDTO {
    private String title;
    private Long memberId;
    private String roadmap;

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}