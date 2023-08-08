package com.darknights.devigation.member.query.domain.repository;

import com.darknights.devigation.member.query.domain.aggregate.entity.QueryMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    List<QueryMember> findAll();

    QueryMember findById(long id);

    QueryMember findByUID(long uid);

    QueryMember findByAccessToken(String accessToken);
}
