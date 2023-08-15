package com.darknights.devigation.member.query.application.dto;

import com.darknights.devigation.member.command.domain.aggregate.entity.enumType.PlatformEnum;
import com.darknights.devigation.member.command.domain.aggregate.entity.enumType.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindMemberDTO {

    private long id;

    private String name;

    private String profileImage;

    private String platform;

    private String role;

    private String email;

    protected FindMemberDTO() {}


    public FindMemberDTO(long id, String name, String email, String profileImage, String platform, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profileImage = profileImage;
        this.platform = platform;
        this.role = role;
    }
}
