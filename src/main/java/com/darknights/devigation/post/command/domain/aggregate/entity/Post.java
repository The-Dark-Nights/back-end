package com.darknights.devigation.post.command.domain.aggregate.entity;

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

    @Column(nullable = false, name = "content", columnDefinition = "TEXT")
    private String content;
    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

}
