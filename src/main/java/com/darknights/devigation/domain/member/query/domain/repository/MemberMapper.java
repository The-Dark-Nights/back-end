package com.darknights.devigation.domain.member.query.domain.repository;

import com.darknights.devigation.domain.member.query.domain.aggregate.entity.QueryMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    List<QueryMember> findAll();

    QueryMember findById(long id);

    QueryMember findByUID(String uid);

    QueryMember findByAccessToken(String accessToken);

    QueryMember findByEmail(String email);
}
