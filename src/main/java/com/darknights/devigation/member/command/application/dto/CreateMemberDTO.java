package com.darknights.devigation.member.command.application.dto;

import com.darknights.devigation.member.command.domain.aggregate.entity.enumType.PlatformEnum;
import com.darknights.devigation.member.command.domain.aggregate.entity.enumType.Role;
import lombok.Getter;

@Getter
public class CreateMemberDTO {
    private final Long UID;
    private final String name;
    private final Role role;
    private final String profileImage;
    private final String accessToken;
    private final PlatformEnum platformEnum;

    public CreateMemberDTO(Long UID, String name, Role role, String profileImage, String accessToken, PlatformEnum platformEnum) {
        this.UID = UID;
        this.name = name;
        this.role = role;
        this.profileImage = profileImage;
        this.accessToken = accessToken;
        this.platformEnum = platformEnum;
    }
}
