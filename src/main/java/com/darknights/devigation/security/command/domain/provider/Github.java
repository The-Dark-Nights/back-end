package com.darknights.devigation.security.command.domain.provider;

import com.darknights.devigation.member.query.domain.aggregate.entity.enumType.PlatformEnum;

import java.util.Map;

public class Github extends OAuth2UserInfo {

    public Github(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {

        return String.valueOf(attributes.get("id"));
    }

    @Override
    public String getName() {

        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {

        return (String) attributes.get("email");
    }

    @Override
    public String getImageUrl() {

        return (String) attributes.get("avatar_url");
    }

    @Override
    public String getProvider(){
        return PlatformEnum.GITHUB.name();
    }
}
