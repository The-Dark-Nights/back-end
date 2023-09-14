package com.darknights.devigation.global.security.command.application.service;

import com.darknights.devigation.global.security.token.UserPrincipal;
import com.darknights.devigation.domain.member.query.application.dto.FindMemberDTO;
import com.darknights.devigation.domain.member.query.application.service.FindMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final FindMemberService findMemberService;

    @Autowired
    public CustomUserDetailService(FindMemberService findMemberService) {
        this.findMemberService = findMemberService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        FindMemberDTO member = findMemberService.findByEmail(email);
        System.out.println("member = " + member);
        return UserPrincipal.create(member);
    }

    public UserDetails loadUserById(Long id) {
        FindMemberDTO member = findMemberService.findById(id);
        return UserPrincipal.create(member);
    }
}
