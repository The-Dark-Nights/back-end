package com.darknights.devigation.domain.roadmap.command.domain.aggregate.vo;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor
public class SourceCategoryVO {
    @Column(name = "source_category_id", nullable = false)
    private long id;

    public SourceCategoryVO(long id){
        this. id =id;
    }
}