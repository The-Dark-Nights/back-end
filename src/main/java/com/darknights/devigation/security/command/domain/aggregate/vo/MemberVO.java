package com.darknights.devigation.security.command.domain.aggregate.vo;


import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
public class MemberVO {
    @Column(nullable = false, name = "member_id")
    private long id;

    public MemberVO(long id) {
        this.id = id;
    }

    protected MemberVO() {}
}
