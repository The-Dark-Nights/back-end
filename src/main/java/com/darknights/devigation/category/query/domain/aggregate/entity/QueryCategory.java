package com.darknights.devigation.category.query.domain.aggregate.entity;

import com.darknights.devigation.category.query.domain.aggregate.vo.MemberVO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "CATEGOTY_TB")
@Getter
@NoArgsConstructor
public class QueryCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Embedded
    private MemberVO memberId;
}