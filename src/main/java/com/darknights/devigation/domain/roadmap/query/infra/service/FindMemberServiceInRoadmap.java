package com.darknights.devigation.domain.roadmap.query.infra.service;

import com.darknights.devigation.domain.member.query.application.dto.FindMemberDTO;
import com.darknights.devigation.domain.member.query.application.service.FindMemberService;
import org.springframework.stereotype.Service;

@Service
public class FindMemberServiceInRoadmap implements com.darknights.devigation.domain.roadmap.query.domain.service.FindMemberServiceInRoadmap {

    private final FindMemberService findMemberService;

    public FindMemberServiceInRoadmap(FindMemberService findMemberService) {
        this.findMemberService = findMemberService;
    }

    @Override
    public FindMemberDTO FindMember(Long memberId) {
        return findMemberService.findById(memberId);
    }
}
