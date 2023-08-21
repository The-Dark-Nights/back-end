package com.darknights.devigation.security.command.domain.aggregate.entity;


import com.darknights.devigation.security.command.domain.aggregate.vo.MemberVO;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "TOKEN_TB")
@Getter
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private MemberVO member;

    @Column(length = 1024, nullable = false, name = "access_token")
    private String accessToken;

    protected Token() {}

    public Token(MemberVO member, String accessToken) {
        this.member = member;
        this.accessToken = accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
