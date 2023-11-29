package com.darknights.devigation.domain.roadmap.query.command.application.dto;

import com.darknights.devigation.domain.member.query.application.dto.FindMemberDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QueryRoadmapDTO {
    private Long id;
    private String title;
    private String roadmap;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Long memberId;
    private FindMemberDTO findMemberDTO;

    public void setFindMemberDTO(FindMemberDTO findMemberDTO) {
        this.findMemberDTO = findMemberDTO;
    }
}
