package com.darknights.devigation.post.command.application.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class CreatePostDTO {
    private Long memberId;
    private Long categoryId;
    private String content;


}
