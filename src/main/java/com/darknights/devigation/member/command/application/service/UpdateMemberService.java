package com.darknights.devigation.member.command.application.service;

import com.darknights.devigation.member.command.application.dto.UpdateMemberDTO;
import com.darknights.devigation.member.command.domain.aggregate.entity.Member;
import com.darknights.devigation.member.command.domain.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UpdateMemberService {

    private final MemberRepository memberRepository;

    public UpdateMemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public boolean update(long memberId, UpdateMemberDTO updateMemberDTO) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (findMember.isPresent()) {
            Member updateMember = findMember.get();
            if (updateMemberDTO.getProfileImage() != null) {
                updateMember.setProfileImage(updateMemberDTO.getProfileImage());
            }
            return true;
        } else {
            return false;
        }
    }
}
