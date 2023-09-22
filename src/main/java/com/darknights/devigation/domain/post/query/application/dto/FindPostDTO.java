package com.darknights.devigation.domain.post.query.application.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FindPostDTO {

    private Long id;

    private String title;

    private Long memberId;

    private Long categoryId;

    private String content;

    private LocalDateTime createdDate;

    private boolean published;

    @Override
    public String toString() {
        return "FindPostDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", memberId=" + memberId +
                ", categoryId=" + categoryId +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                ", published=" + published +
                '}';
    }
}
