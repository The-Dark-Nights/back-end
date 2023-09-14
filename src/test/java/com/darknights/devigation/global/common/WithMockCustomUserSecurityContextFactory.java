package com.darknights.devigation.global.common;

import com.darknights.devigation.domain.member.command.domain.aggregate.entity.enumType.PlatformEnum;
import com.darknights.devigation.domain.member.command.domain.aggregate.entity.enumType.Role;
import com.darknights.devigation.domain.member.query.application.dto.FindMemberDTO;
import com.darknights.devigation.global.security.token.UserPrincipal;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {
    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser annotation) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();


        FindMemberDTO mockFindMemberDTO = new FindMemberDTO(
                0L,
                "mockName",
                annotation.email(),
                "profileImage",
                annotation.provider(),
                annotation.role()
        );

        UserPrincipal principal = UserPrincipal.create(mockFindMemberDTO);
        Authentication auth = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
        context.setAuthentication(auth);
        return context;
    }
}
