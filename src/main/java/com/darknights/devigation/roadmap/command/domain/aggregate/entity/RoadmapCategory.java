package com.darknights.devigation.roadmap.command.domain.aggregate.entity;


import com.darknights.devigation.roadmap.command.domain.aggregate.vo.CategoryVO;
import com.darknights.devigation.roadmap.command.domain.aggregate.vo.ChildCategoryVO;
import com.darknights.devigation.roadmap.command.domain.aggregate.vo.ParentCategoryVO;
import com.darknights.devigation.roadmap.command.domain.aggregate.vo.RoadmapVO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="ROADMAP_CATEGORY_TB")
@NoArgsConstructor
@Getter
public class RoadmapCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private CategoryVO categoryId;

    @Embedded
    private RoadmapVO roadmapId;

    @Column(nullable = false)
    private String position;

    @Embedded
    private ChildCategoryVO childCategoryId;

    @Embedded
    private ParentCategoryVO parentCategoryId;

    public RoadmapCategory(long categoryId,long roadmapId, String position, long childCategoryId, long parentCategoryId) {
        this.categoryId = new CategoryVO(categoryId);
        this.roadmapId = new RoadmapVO(roadmapId);
        this.position = position;
        this.childCategoryId = new ChildCategoryVO(childCategoryId);
        this.parentCategoryId = new ParentCategoryVO(parentCategoryId);
    }

    public RoadmapCategory(long id,long categoryId, long roadmapId, String position, long childCategoryId, long parentCategoryId) {
        this.id= id;
        this.categoryId = new CategoryVO(categoryId);
        this.roadmapId = new RoadmapVO(roadmapId);
        this.position = position;
        this.childCategoryId = new ChildCategoryVO(childCategoryId);
        this.parentCategoryId = new ParentCategoryVO(parentCategoryId);
    }
}
