package com.darknights.devigation.domain.roadmap.query.domain.aggregate.vo;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
public class RoadmapVO {
    @Column(name = "roadmap_id", nullable = false)
    private Long id;

    public RoadmapVO(long id){
        this.id =id;
    }

    protected RoadmapVO() {};
}