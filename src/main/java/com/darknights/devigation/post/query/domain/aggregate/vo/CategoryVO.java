package com.darknights.devigation.post.query.domain.aggregate.vo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryVO {
    @Column(nullable = false, name="category_id")
    private long id;

    @Builder
    public CategoryVO(long categoryId){this.id=categoryId;}
}
