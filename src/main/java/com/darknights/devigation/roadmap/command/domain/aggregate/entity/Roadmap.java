package com.darknights.devigation.roadmap.command.domain.aggregate.entity;


import com.darknights.devigation.roadmap.command.domain.aggregate.vo.MemberVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="ROADMAP_TB")
@NoArgsConstructor
@Getter
@ToString
@EntityListeners(AutoCloseable.class)
public class Roadmap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Embedded
    private MemberVO memberId;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    public Roadmap(String title, long memberId) {
        this.title = title;
        this.memberId = new MemberVO(memberId);
    }

    public Roadmap(long id, String title, MemberVO memberId, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.memberId = memberId;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public Roadmap(long id, String title, long memberId, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.memberId = new MemberVO(memberId);
        this.modifiedDate = modifiedDate;
    }


    public Roadmap(long id, String title, long memberId) {
        this.id = id;
        this.title = title;
        this.memberId = new MemberVO(memberId);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
