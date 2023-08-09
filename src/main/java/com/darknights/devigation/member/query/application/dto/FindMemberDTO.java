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

    private String AccessToken;

    private String profileImage;

    private String platform;

    private String role;

    protected FindMemberDTO() {}

    public FindMemberDTO(long id, String name, String accessToken, String profileImage, String platform, String role) {
        this.id = id;
        this.name = name;
        AccessToken = accessToken;
        this.profileImage = profileImage;
        this.platform = platform;
        this.role = role;
    }
}
