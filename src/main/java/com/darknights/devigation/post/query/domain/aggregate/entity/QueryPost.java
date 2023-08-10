package com.darknights.devigation.post.query.domain.aggregate.entity;

import com.darknights.devigation.post.query.domain.aggregate.vo.CategoryVO;
import com.darknights.devigation.roadmap.query.domain.aggregate.vo.MemberVO;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="POST_TB")
@Getter
public class QueryPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Embedded
    private MemberVO memberId;

    @Embedded
    private CategoryVO categoryId;

    @Column(nullable = false, name = "content", columnDefinition = "TEXT")
    private String content;
    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column
    private boolean published;
}
