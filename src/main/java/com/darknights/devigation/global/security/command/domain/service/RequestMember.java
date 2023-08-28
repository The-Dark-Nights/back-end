package com.darknights.devigation.global.security.command.domain.service;

import com.darknights.devigation.domain.member.query.application.dto.FindMemberDTO;

public interface RequestMember {
    FindMemberDTO getMemberById(long memberId);
}
