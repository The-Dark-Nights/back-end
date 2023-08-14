package com.darknights.devigation.roadmap.command.domain.aggregate.entity;


import com.darknights.devigation.roadmap.command.domain.aggregate.vo.ChildCategoryVO;
import com.darknights.devigation.roadmap.command.domain.aggregate.vo.ParentCategoryVO;
import com.darknights.devigation.roadmap.command.domain.aggregate.vo.RoadmapVO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="CATEGORY_IN_ROADMAP_TB")
@NoArgsConstructor
@Getter
public class RoadmapCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private RoadmapVO roadmapId;

    @Column(nullable = false)
    private String position;

    @Embedded
    private ChildCategoryVO childCategoryId;

    @Embedded
    private ParentCategoryVO parentCategoryId;

}
