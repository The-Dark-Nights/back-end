package com.darknights.devigation.roadmap.command.domain.aggregate.entity;


import com.darknights.devigation.roadmap.command.domain.aggregate.vo.CategoryVO;
import com.darknights.devigation.roadmap.command.domain.aggregate.vo.RoadmapVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="ROADMAP_NODE_TB")
@NoArgsConstructor
@Getter
@ToString
public class RoadmapNode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private RoadmapVO roadmapId;

    @Embedded
    private CategoryVO categoryId;


    @Column(nullable = false)
    private String position;

    public RoadmapNode(long roadmapId,long categoryId, String position) {
        this.roadmapId = new RoadmapVO(roadmapId);
        this.categoryId = new CategoryVO(categoryId);
        this.position = position;
    }
}
