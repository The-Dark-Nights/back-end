package com.darknights.devigation.security.command.domain.service;

import com.darknights.devigation.member.query.application.dto.FindMemberDTO;

public interface RequestMember {
    FindMemberDTO getMemberById(long memberId);
}
