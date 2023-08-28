package com.darknights.devigation.domain.roadmap.query.domain.aggregate.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="ROADMAP_CATEGORY_TB")
@Getter
@ToString

public class QueryRoadmapCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_id",nullable = false, unique = true)
    private long categoryId;

    @Column(name = "roadmap_id",nullable = false)
    private long roadmapId;

    @Column(nullable = false)
    private String position;

    @Column(name = "child_category_id")
    private long childCategoryId;

    @Column (name = "parent_category_id")
    private long parentCategoryId;
//    @Embedded
//    private CategoryVO category;
//
//    @Embedded
//    private RoadmapVO roadmap;
//
//    @Column(nullable = false)
//    private String position;
//
//    @Embedded
//    private ChildCategoryVO childCategory;
//
//    @Embedded
//    private ParentCategoryVO parentCategory;
//    protected QueryRoadmapCategory(){};
//
//    public QueryRoadmapCategory(Long id, CategoryVO category, RoadmapVO roadmap, String position, ChildCategoryVO childCategory, ParentCategoryVO parentCategory) {
//        this.id = id;
//        this.category = category;
//        this.roadmap = roadmap;
//        this.position = position;
//        this.childCategory = childCategory;
//        this.parentCategory = parentCategory;
//    }
}
