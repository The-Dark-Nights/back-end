package com.darknights.devigation.domain.roadmap.query.domain.service;

import com.darknights.devigation.domain.member.query.application.dto.FindMemberDTO;

public interface FindMemberServiceInRoadmap {
    FindMemberDTO FindMember(Long memberId);
}
