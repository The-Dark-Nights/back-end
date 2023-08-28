package com.darknights.devigation.domain.member.command.application.service;

import com.darknights.devigation.domain.member.command.application.dto.CreateMemberDTO;
import com.darknights.devigation.domain.member.command.domain.aggregate.entity.Member;
import com.darknights.devigation.domain.member.command.domain.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateMemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public CreateMemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member create(CreateMemberDTO createMemberDTO) {
        Member createdMember = new Member(
                createMemberDTO.getName(),
                createMemberDTO.getUID(),
                createMemberDTO.getProfileImage(),
                createMemberDTO.getEmail(),
                createMemberDTO.getPlatformEnum(),
                createMemberDTO.getRole()
        );
        return memberRepository.save(createdMember);
    }
}
