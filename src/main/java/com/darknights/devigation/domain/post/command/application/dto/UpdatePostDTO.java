package com.darknights.devigation.domain.post.command.application.dto;


import com.darknights.devigation.domain.post.command.domain.aggregate.vo.CategoryVO;
import com.darknights.devigation.domain.post.command.domain.aggregate.vo.MemberVO;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdatePostDTO {
    private Long id;
    private String title;
    private Long memberId;  // 필요 없을 수도 있음
    private Long categoryId;
    private String content;
    private LocalDateTime createdDate; // 필요 없을 수도 있음
    private boolean published;

    public UpdatePostDTO(Long id, String title, MemberVO memberId, CategoryVO categoryId, String content, LocalDateTime createdDate, boolean published) {
        this.id = id;
        this.title = title;
        this.memberId = memberId.getId();
        this.categoryId = categoryId.getId();
        this.content = content;
        this.createdDate = createdDate;
        this.published = published;
    }

    public UpdatePostDTO(Long id, String title, Long memberId, Long categoryId, String content, LocalDateTime createdDate, boolean published) {
        this.id = id;
        this.title = title;
        this.memberId = memberId;
        this.categoryId = categoryId;
        this.content = content;
        this.createdDate = createdDate;
        this.published = published;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    // 삭제 test시 출력용 toString 메소드
    @Override
    public String toString() {
        return "UpdatePostDTO{" +
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
