package com.darknights.devigation.domain.member.query.domain.aggregate.entity;


import com.darknights.devigation.domain.member.command.domain.aggregate.entity.enumType.PlatformEnum;
import com.darknights.devigation.domain.member.command.domain.aggregate.entity.enumType.Role;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="MEMBER_TB")
@Getter
public class QueryMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = true, name = "access_token")
    private String accessToken;

    @Column(nullable = false, name = "uid")
    private String UID;

    @Column(nullable = false, name = "email", unique = true)
    private String email;

    @Column(length = 300, name = "profile_image", nullable = false)
    private String profileImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlatformEnum platform;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @CreatedDate
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    protected QueryMember() {};
}
