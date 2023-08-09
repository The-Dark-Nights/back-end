package com.darknights.devigation.member.query.application.service;

import com.darknights.devigation.member.query.application.dto.FindMemberDTO;
import com.darknights.devigation.member.query.domain.aggregate.entity.QueryMember;
import com.darknights.devigation.member.query.domain.repository.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindMemberService {

    private final MemberMapper memberMapper;
    @Autowired
    public FindMemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public FindMemberDTO findByUID(String uid) {
        QueryMember findMember = memberMapper.findByUID(uid);
        return new FindMemberDTO(
                findMember.getId(),
                findMember.getName(),
                findMember.getAccessToken(),
                findMember.getProfileImage(),
                findMember.getPlatform().name(),
                findMember.getRole().name()
        );
    }

    public FindMemberDTO findByAccessToken(String accessToken) {
        QueryMember findMember = memberMapper.findByAccessToken(accessToken);
        return new FindMemberDTO(
                findMember.getId(),
                findMember.getName(),
                findMember.getAccessToken(),
                findMember.getProfileImage(),
                findMember.getPlatform().name(),
                findMember.getRole().name()
        );
    }

    public FindMemberDTO findById(long memberId) {

        QueryMember findMember = memberMapper.findById(memberId);

        return new FindMemberDTO(
                findMember.getId(),
                findMember.getName(),
                findMember.getAccessToken(),
                findMember.getProfileImage(),
                findMember.getPlatform().name(),
                findMember.getRole().name()
        );
    }
}
