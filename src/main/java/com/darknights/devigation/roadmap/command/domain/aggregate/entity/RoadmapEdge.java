package com.darknights.devigation.roadmap.command.domain.aggregate.entity;

import com.darknights.devigation.roadmap.command.domain.aggregate.vo.RoadmapVO;
import com.darknights.devigation.roadmap.command.domain.aggregate.vo.SourceCategoryVO;
import com.darknights.devigation.roadmap.command.domain.aggregate.vo.TargetCategoryVO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="ROADMAP_EDGE_TB")
@NoArgsConstructor
@Getter
public class RoadmapEdge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private RoadmapVO roadmapId;

    @Column(name = "edge_id")
    private String edgeId;

    @Embedded
    private SourceCategoryVO sourceCategoryId;

    @Embedded
    private TargetCategoryVO targetCategoryId;
}
