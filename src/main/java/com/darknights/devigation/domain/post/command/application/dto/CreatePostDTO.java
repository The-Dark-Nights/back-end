package com.darknights.devigation.domain.post.command.application.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "글 생성 객체")
public class CreatePostDTO {
    @Schema(description = "게시글 제목", defaultValue = "디폴트 제목")
    private String title;

    @Schema(description = "작성자 아이디", defaultValue = "1L")
    private Long memberId;

    @Schema(description = "카테고리 아이디", defaultValue = "1L")
    private Long categoryId;

    @Schema(description = "게시글 내용", defaultValue = "디폴트 내용")
    private String content;

    @Schema(description = "발행 여부", defaultValue = "true")
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
