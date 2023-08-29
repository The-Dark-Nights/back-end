package com.darknights.devigation.domain.member.command.application.dto;

import com.darknights.devigation.domain.member.command.domain.aggregate.entity.enumType.PlatformEnum;
import com.darknights.devigation.domain.member.command.domain.aggregate.entity.enumType.Role;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UpdateMemberDTO {

    private String UID;
    private String name;
    private Role role;
    private String profileImage;
    private PlatformEnum platformEnum;

    public UpdateMemberDTO(String UID, String name, Role role, String profileImage, PlatformEnum platformEnum) {
        this.UID = UID;
        this.name = name;
        this.role = role;
        this.profileImage = profileImage;
        this.platformEnum = platformEnum;
    }

    public UpdateMemberDTO(String profileImage, String name) {
        this.profileImage = profileImage;
        this.name = name;
    }
}
