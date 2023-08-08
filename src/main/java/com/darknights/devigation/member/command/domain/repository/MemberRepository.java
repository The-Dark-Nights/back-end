package com.darknights.devigation.member.command.domain.repository;

import com.darknights.devigation.member.command.domain.aggregate.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
