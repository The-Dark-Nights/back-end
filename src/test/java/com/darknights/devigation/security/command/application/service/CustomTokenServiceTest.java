package com.darknights.devigation.security.command.application.service;
import com.darknights.devigation.member.command.application.dto.CreateMemberDTO;
import com.darknights.devigation.member.command.domain.aggregate.entity.enumType.PlatformEnum;
import com.darknights.devigation.member.command.domain.aggregate.entity.enumType.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootTest
@Transactional
class CustomTokenServiceTest {

    @Autowired
    private CustomTokenService customTokenService;

    @Value("${app.auth.tokenSecret}")
    private String JWT_SECRET;

    @Value("${app.auth.tokenExpirationMsec}")
    private int JWT_EXPIRATION_MS;

    private static Stream<Arguments> getTokenInfo() {
        return Stream.of(
                Arguments.of(
                        1L, Role.MEMBER.name()
                )
        );
    }

    @DisplayName("JWT 토큰이 정상적으로 생성되는지 확인")
    @ParameterizedTest
    @MethodSource("getTokenInfo")
    void TestCreateAccessToken (long memberId, String memberRole) {

        String tokenValue = customTokenService.createToken(memberId, memberRole);

        Assertions.assertNotNull(tokenValue);
    }

    @DisplayName("JWT 토큰의 member Id가 정상적으로 생성되는지 확인")
    @ParameterizedTest
    @MethodSource("getTokenInfo")
    void TestAccessTokenUsesMemberId (long memberId, String memberRole) {

        String tokenValue = customTokenService.createToken(memberId, memberRole);
        String subject = Jwts.parserBuilder()
                .setSigningKey(JWT_SECRET)
                .build()
                .parseClaimsJws(tokenValue)
                .getBody()
                .getSubject();

        Assertions.assertEquals(Long.parseLong(subject), memberId);
    }

    @DisplayName("JWT 토큰의 member Role이 정상적으로 생성되는지 확인")
    @ParameterizedTest
    @MethodSource("getTokenInfo")
    void TestAccessTokenUsesMemberRole (long memberId, String memberRole) {

        String tokenValue = customTokenService.createToken(memberId, memberRole);
        String tokenRole = (String) Jwts.parserBuilder()
                .setSigningKey(JWT_SECRET)
                .build()
                .parseClaimsJws(tokenValue)
                .getBody()
                .get("role");

        Assertions.assertEquals(tokenRole, memberRole);
    }

    @DisplayName("JWT 토큰의 issueDate가 정상적으로 생성되는지 확인")
    @ParameterizedTest
    @MethodSource("getTokenInfo")
    void TestAccessTokenUsesIssueDate (long memberId, String memberRole) {

        String tokenValue = customTokenService.createToken(memberId, memberRole);
        Date issuedAt = Jwts.parserBuilder()
                .setSigningKey(JWT_SECRET)
                .build()
                .parseClaimsJws(tokenValue)
                .getBody()
                .getIssuedAt();

        Assertions.assertNotNull(issuedAt);
    }

    @DisplayName("JWT 토큰의 만료 시간이 정상적으로 생성되는지 확인")
    @ParameterizedTest
    @MethodSource("getTokenInfo")
    void TestAccessTokenUsesExpirationDate (long memberId, String memberRole) {

        String tokenValue = customTokenService.createToken(memberId, memberRole);
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(JWT_SECRET)
                .build()
                .parseClaimsJws(tokenValue)
                .getBody();

        Date issuedAt = claims.getIssuedAt();
        Date expiration = claims.getExpiration();

        Assertions.assertEquals(expiration.getTime() - issuedAt.getTime() , JWT_EXPIRATION_MS);
    }

}