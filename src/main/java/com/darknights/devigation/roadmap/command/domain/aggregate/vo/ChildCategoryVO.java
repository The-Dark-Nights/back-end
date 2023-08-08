package com.darknights.devigation.roadmap.command.domain.aggregate.vo;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor
public class ChildCategoryVO {
    @Column(name = "child_category_id")
    private long childCategoryId;

    public ChildCategoryVO(long id){
        this.childCategoryId =id;
    }
}