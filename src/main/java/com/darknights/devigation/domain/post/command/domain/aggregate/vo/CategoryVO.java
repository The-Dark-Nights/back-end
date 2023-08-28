package com.darknights.devigation.domain.post.command.domain.aggregate.vo;

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
    private Long id;

    @Builder
    public CategoryVO(Long categoryId){this.id=categoryId;}
}
