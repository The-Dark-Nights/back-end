package com.darknights.devigation.domain.post.command.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Schema(description = "글 응답 객체")
public class ResponsePostDTO {

    private Long id;

    private String title;

    private Long memberId;

    private Long categoryId;

    private String content;

    private LocalDateTime createdDate;

    private boolean published;

    public ResponsePostDTO(Long id, String title, Long memberId, Long categoryId, String content, LocalDateTime createdDate, boolean published) {
        this.id = id;
        this.title = title;
        this.memberId = memberId;
        this.categoryId = categoryId;
        this.content = content;
        this.createdDate = createdDate;
        this.published = published;
    }

    @Override
    public String toString() {
        return "ResponsePostDTO{" +
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
