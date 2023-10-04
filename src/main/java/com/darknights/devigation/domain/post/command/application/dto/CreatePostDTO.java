package com.darknights.devigation.domain.post.command.application.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class CreatePostDTO {
    private String title;
    private Long memberId;
    private Long categoryId;
    private String content;
    private boolean published;

    public CreatePostDTO() {}

    public CreatePostDTO(String title, Long memberId, Long categoryId, String content, boolean published) {
        this.title = title;
        this.memberId = memberId;
        this.categoryId = categoryId;
        this.content = content;
        this.published = published;
    }
}
