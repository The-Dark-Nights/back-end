package com.darknights.devigation.roadmap.command.domain.aggregate.entity;


import com.darknights.devigation.roadmap.command.domain.aggregate.vo.MemberVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="ROADMAP_TB")
@NoArgsConstructor
@Getter
public class Roadmap {

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
