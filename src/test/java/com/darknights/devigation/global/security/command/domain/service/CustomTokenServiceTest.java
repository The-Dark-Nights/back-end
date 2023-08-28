package com.darknights.devigation.global.security.command.domain.service;

import com.darknights.devigation.global.security.command.domain.service.CustomTokenService;
import com.darknights.devigation.domain.member.command.domain.aggregate.entity.enumType.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@SpringBootTest
@Transactional
class CustomTokenServiceTest {

    @Autowired
    private CustomTokenService customTokenService;

    private static Stream<Arguments> getTokenInfo() {
        return Stream.of(
                Arguments.of(
                        1L, Role.MEMBER.name()
                )
        );
    }

    @DisplayName("JWT 토큰을 통해 맴버의 id를 정상적으로 출력되는지 테스트")
    @ParameterizedTest
    @MethodSource("getTokenInfo")
    void testGetMemberIdFromToken(long memberId, String memberRole) {
        String tokenValue = customTokenService.createToken(memberId, memberRole);
        Long getMemberIdFromToken = customTokenService.getUserIdFromToken(tokenValue);
        Assertions.assertEquals(memberId, getMemberIdFromToken);

    }
}