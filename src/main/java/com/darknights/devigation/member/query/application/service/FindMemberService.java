package com.darknights.devigation.member.query.application.service;

import com.darknights.devigation.member.query.application.dto.FindMemberDTO;
import com.darknights.devigation.member.query.domain.aggregate.entity.QueryMember;
import com.darknights.devigation.member.query.domain.repository.MemberMapper;
import com.darknights.devigation.security.command.domain.exception.UserNotFoundException;
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
        if(findMember == null) {
            return null;
        } else {
            return new FindMemberDTO(
                    findMember.getId(),
                    findMember.getName(),
                    findMember.getEmail(),
                    findMember.getProfileImage(),
                    findMember.getPlatform().name(),
                    findMember.getRole().name()
            );
        }
    }

    public FindMemberDTO findByAccessToken(String accessToken) {
        QueryMember findMember = memberMapper.findByAccessToken(accessToken);
        return new FindMemberDTO(
                findMember.getId(),
                findMember.getName(),
                findMember.getEmail(),
                findMember.getProfileImage(),
                findMember.getPlatform().name(),
                findMember.getRole().name()
        );
    }

    public FindMemberDTO findById(long memberId) throws UserNotFoundException {

        QueryMember findMember = memberMapper.findById(memberId);

        if(findMember == null) {
            throw new UserNotFoundException("해당 Id를 가진 사용자를 찾을 수 없습니다.");
        }

        return new FindMemberDTO(
                findMember.getId(),
                findMember.getName(),
                findMember.getEmail(),
                findMember.getProfileImage(),
                findMember.getPlatform().name(),
                findMember.getRole().name()
        );
    }

    public FindMemberDTO  findByEmail(String email) {

        QueryMember findMember = memberMapper.findByEmail(email);

        return new FindMemberDTO(
                findMember.getId(),
                findMember.getName(),
                findMember.getEmail(),
                findMember.getProfileImage(),
                findMember.getPlatform().name(),
                findMember.getRole().name()
        );
    }
}
