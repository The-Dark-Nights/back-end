package com.darknights.devigation.global.security.command.application.service;

import com.darknights.devigation.global.security.command.domain.provider.OAuth2UserInfo;
import com.darknights.devigation.global.security.command.domain.provider.OAuth2UserInfoFactory;
import com.darknights.devigation.global.security.token.UserPrincipal;
import com.darknights.devigation.domain.member.command.application.dto.CreateMemberDTO;
import com.darknights.devigation.domain.member.command.application.dto.UpdateMemberDTO;
import com.darknights.devigation.domain.member.command.application.service.CreateMemberService;
import com.darknights.devigation.domain.member.command.application.service.UpdateMemberService;
import com.darknights.devigation.domain.member.command.domain.aggregate.entity.Member;
import com.darknights.devigation.domain.member.command.domain.aggregate.entity.enumType.PlatformEnum;
import com.darknights.devigation.domain.member.command.domain.aggregate.entity.enumType.Role;
import com.darknights.devigation.domain.member.query.application.dto.FindMemberDTO;
import com.darknights.devigation.domain.member.query.application.service.FindMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final FindMemberService findMemberService;
    private final CreateMemberService createMemberService;
    private final UpdateMemberService updateMemberService;

    @Autowired
    public CustomOAuth2UserService(FindMemberService findMemberService, CreateMemberService createMemberService, UpdateMemberService updateMemberService) {
        this.findMemberService = findMemberService;
        this.createMemberService = createMemberService;
        this.updateMemberService = updateMemberService;
    }


    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
            String registrationId = oAuth2UserRequest.getClientRegistration().getRegistrationId(); // 소셜 정보 가져옴

            String userNameAttributeName = oAuth2UserRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

            OAuth2UserInfo attributes = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, oAuth2User.getAttributes());

            UserPrincipal socialMember =  saveOrUpdate(attributes, registrationId);
            return socialMember;
    }

    private UserPrincipal saveOrUpdate(OAuth2UserInfo attributes, String provider) {
        FindMemberDTO member = findMemberService.findByUID(attributes.getId());
        UserPrincipal oauthMember;
        if (member == null) {
            CreateMemberDTO createMemberDTO = new CreateMemberDTO(attributes.getId(), attributes.getName(), Role.MEMBER, attributes.getImageUrl(), attributes.getEmail(), PlatformEnum.valueOf(provider.toUpperCase()));
            Member newMember = createMemberService.create(createMemberDTO);
            oauthMember = UserPrincipal.create(newMember, attributes.getAttributes());
        } else {
            UpdateMemberDTO updateMemberDTO = new UpdateMemberDTO(attributes.getImageUrl(), attributes.getName());
            boolean updateMemberResult = updateMemberService.update(member.getId(), updateMemberDTO);
            oauthMember = UserPrincipal.create(member, attributes.getAttributes());
        }
        return oauthMember;
    }

}
