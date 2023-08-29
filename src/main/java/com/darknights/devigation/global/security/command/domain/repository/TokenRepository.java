package com.darknights.devigation.global.security.command.domain.repository;

import com.darknights.devigation.global.security.command.domain.aggregate.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findTokenByMember_Id(long memberId);
    Optional<Token> findTokenByAccessToken(String accessToken);
}
