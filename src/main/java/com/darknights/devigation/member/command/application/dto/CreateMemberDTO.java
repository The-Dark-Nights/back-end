package com.darknights.devigation.member.command.application.dto;

import com.darknights.devigation.member.command.domain.aggregate.entity.enumType.PlatformEnum;
import com.darknights.devigation.member.command.domain.aggregate.entity.enumType.Role;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateMemberDTO {
    private final String UID;
    private final String name;
    private final Role role;
    private final String profileImage;
    private final String email;
    private final PlatformEnum platformEnum;

    public CreateMemberDTO(String UID, String name, Role role, String email, String profileImage, PlatformEnum platformEnum) {
        this.UID = UID;
        this.name = name;
        this.role = role;
        this.email = email;
        this.profileImage = profileImage;
        this.platformEnum = platformEnum;
    }
}
