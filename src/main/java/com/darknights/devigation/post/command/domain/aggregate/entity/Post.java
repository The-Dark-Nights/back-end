package com.darknights.devigation.post.command.domain.aggregate.entity;

import com.darknights.devigation.post.command.application.dto.UpdatePostDTO;
import com.darknights.devigation.post.command.domain.aggregate.vo.CategoryVO;
import com.darknights.devigation.post.command.domain.aggregate.vo.MemberVO;
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

    @Embedded
    private MemberVO memberId;

    @Embedded
    private CategoryVO categoryId;

    @Column(nullable = false, name = "content", columnDefinition = "TEXT")
    private String content;
    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    public Post(MemberVO memberId, CategoryVO categoryId, String content) {
        this.memberId = memberId;
        this.categoryId = categoryId;
        this.content = content;
        this.createdDate = LocalDateTime.now();
    }
    public Post(UpdatePostDTO updatePostDTO){
        this.id=updatePostDTO.getId();
        this.memberId= new MemberVO(updatePostDTO.getMemberId());
        this.categoryId= new CategoryVO(updatePostDTO.getCategoryId());
        this.content=updatePostDTO.getContent();
        this.createdDate=updatePostDTO.getCreatedDate();
    }
}
