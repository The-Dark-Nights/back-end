package com.darknights.devigation.roadmap.command.domain.aggregate.entity;


import com.darknights.devigation.roadmap.command.domain.aggregate.vo.CategoryVO;
import com.darknights.devigation.roadmap.command.domain.aggregate.vo.RoadmapVO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="ROADMAP_NODE_TB")
@NoArgsConstructor
@Getter
public class RoadmapNode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private CategoryVO categoryId;

    @Embedded
    private RoadmapVO roadmapId;

    @Column(nullable = false)
    private String position;


}
