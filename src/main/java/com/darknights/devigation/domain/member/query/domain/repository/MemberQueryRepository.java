package com.darknights.devigation.domain.member.query.domain.repository;

import com.darknights.devigation.domain.member.query.domain.aggregate.entity.QueryMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberQueryRepository extends JpaRepository<QueryMember, Long> {
}
