package com.darknights.devigation.post.query.domain.entity;

import com.darknights.devigation.post.command.domain.aggregate.vo.MemberVO;
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

    @Embedded
    private MemberVO memberId;

    @Column(nullable = false, name = "content", columnDefinition = "TEXT")
    private String content;
    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
