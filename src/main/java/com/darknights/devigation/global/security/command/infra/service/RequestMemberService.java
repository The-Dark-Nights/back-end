package com.darknights.devigation.global.security.command.infra.service;


import com.darknights.devigation.global.common.annotation.InfraService;
import com.darknights.devigation.global.security.command.domain.service.RequestMember;
import com.darknights.devigation.domain.member.query.application.dto.FindMemberDTO;
import com.darknights.devigation.domain.member.query.application.service.FindMemberService;
import org.springframework.beans.factory.annotation.Autowired;

@InfraService
public class RequestMemberService implements RequestMember {

    private final FindMemberService findMemberService;

    @Autowired
    public RequestMemberService(FindMemberService findMemberService) {
        this.findMemberService = findMemberService;
    }

    @Override
    public FindMemberDTO getMemberById(long memberId) {
        return findMemberService.findById(memberId);
    }
}
