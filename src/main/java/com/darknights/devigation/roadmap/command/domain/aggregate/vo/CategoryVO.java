package com.darknights.devigation.roadmap.command.domain.aggregate.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor
public class CategoryVO {
    @Column(name = "category_id", nullable = false)
    private long id;

    public CategoryVO(long id){
        this.id =id;
    }
}
