package com.darknights.devigation.member.command.domain.aggregate.entity;


import com.darknights.devigation.member.command.domain.aggregate.entity.enumType.PlatformEnum;
import com.darknights.devigation.member.command.domain.aggregate.entity.enumType.Role;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="MEMBER_TB")
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false, name = "email", unique = true)
    private String email;

    @Column(nullable = false, name = "uid")
    private String UID;

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

    protected Member() {}

    public Member(String name, String UID, String profileImage, String email ,PlatformEnum platform, Role role) {
        this.name = name;
        this.UID = UID;
        this.profileImage = profileImage;
        this.email = email;
        this.platform = platform;
        this.role = role;
        this.createdDate = LocalDateTime.now();
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
