package com.darknights.devigation.security.command.domain.provider;

import com.darknights.devigation.member.query.domain.aggregate.entity.enumType.PlatformEnum;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(PlatformEnum.GITHUB.name())) {
            return new Github(attributes);
        } else {
            throw new IllegalArgumentException("해당 OAuth2 제공자는 지원하지 않습니다");
        }
    }
}
