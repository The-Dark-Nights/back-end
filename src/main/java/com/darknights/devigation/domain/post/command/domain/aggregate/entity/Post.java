package com.darknights.devigation.domain.post.command.domain.aggregate.entity;

import com.darknights.devigation.domain.post.command.domain.aggregate.vo.MemberVO;
import com.darknights.devigation.domain.post.command.domain.aggregate.vo.CategoryVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name="Post")
@Table(name="POST_TB")
@Getter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Embedded
    private MemberVO memberId;

    @Embedded
    private CategoryVO categoryId;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String content;
    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column
    private boolean published;

    public Post(String title,MemberVO memberId, CategoryVO categoryId, String content, boolean published) {
        this.title=title;
        this.memberId = memberId;
        this.categoryId = categoryId;
        this.content = content;
        this.createdDate = LocalDateTime.now();
        this.published=published;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategoryId(CategoryVO categoryId) {
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
        return "Post{" +
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
