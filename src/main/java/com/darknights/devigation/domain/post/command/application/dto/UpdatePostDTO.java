package com.darknights.devigation.domain.post.command.application.dto;


import com.darknights.devigation.domain.post.command.domain.aggregate.vo.CategoryVO;
import com.darknights.devigation.domain.post.command.domain.aggregate.vo.MemberVO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class UpdatePostDTO {
    private Long id;
    private String title;
    private Long memberId;  // 필요 없을 수도 있음
    private Long categoryId;
    private String content;
    private boolean published;


    public UpdatePostDTO() {}

    public UpdatePostDTO(Long id, String title, MemberVO memberId, CategoryVO categoryId, String content, boolean published) {
        this.id = id;
        this.title = title;
        this.memberId = memberId.getId();
        this.categoryId = categoryId.getId();
        this.content = content;
        this.published = published;
    }

    public UpdatePostDTO(Long id, String title, Long memberId, Long categoryId, String content, boolean published) {
        this.id = id;
        this.title = title;
        this.memberId = memberId;
        this.categoryId = categoryId;
        this.content = content;
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
                ", published=" + published +
                '}';
    }
}
