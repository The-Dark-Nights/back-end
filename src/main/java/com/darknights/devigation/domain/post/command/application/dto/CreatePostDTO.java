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
}
