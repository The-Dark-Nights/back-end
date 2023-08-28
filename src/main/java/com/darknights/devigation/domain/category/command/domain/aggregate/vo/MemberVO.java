package com.darknights.devigation.domain.category.command.domain.aggregate.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor
public class MemberVO {
    @Column(name = "member_id")
    private long id;

    public MemberVO(long id){
        this.id =id;
    }
}
