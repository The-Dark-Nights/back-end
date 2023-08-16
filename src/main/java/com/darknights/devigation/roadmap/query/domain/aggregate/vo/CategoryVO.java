package com.darknights.devigation.roadmap.query.domain.aggregate.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
public class CategoryVO {
    @Column(name = "category_id", nullable = false)
    private long id;

    protected CategoryVO() {
    }

    public CategoryVO(long categoryId){
        this.id =categoryId;
    }
}
