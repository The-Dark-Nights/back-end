package com.darknights.devigation.roadmap.command.domain.aggregate.vo;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor
public class ParentCategoryVO {
    @Column(name = "parent_category_id")
    private long id;

    public ParentCategoryVO(long id){
        this. id =id;
    }
}