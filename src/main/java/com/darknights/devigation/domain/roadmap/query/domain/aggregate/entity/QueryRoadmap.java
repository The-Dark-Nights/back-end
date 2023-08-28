package com.darknights.devigation.domain.roadmap.query.domain.aggregate.entity;


import com.darknights.devigation.domain.roadmap.query.domain.aggregate.vo.MemberVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="ROADMAP_TB")
@NoArgsConstructor
@Getter
public class QueryRoadmap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Embedded
    private MemberVO memberId;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

}