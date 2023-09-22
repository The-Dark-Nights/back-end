package com.darknights.devigation.domain.category.command.domain.aggregate.entity;


import com.darknights.devigation.domain.category.command.domain.aggregate.entity.enumtype.Classification;
import com.darknights.devigation.domain.category.command.domain.aggregate.vo.MemberVO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "CATEGOTY_TB")
@Getter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Embedded
    private MemberVO memberId;

    @Enumerated
    @Column
    private Classification classification;

    public Category(long memberId,String name){
        this.memberId = new MemberVO(memberId);
        this.name =name;
    }

    public Category(String name, long memberId, Classification classification) {
        this.name = name;
        this.memberId = new MemberVO(memberId);
        this.classification = classification;
    }

    public Category(long id, long memberId, String name){
        this.id = id;
        this.memberId = new MemberVO(memberId);
        this.name =name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMemberId(MemberVO memberId) {
        this.memberId = memberId;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }
}

