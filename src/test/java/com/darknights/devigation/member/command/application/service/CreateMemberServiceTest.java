package com.darknights.devigation.member.command.application.service;

import com.darknights.devigation.member.command.application.dto.CreateMemberDTO;
import com.darknights.devigation.member.command.domain.aggregate.entity.enumType.PlatformEnum;
import com.darknights.devigation.member.command.domain.aggregate.entity.enumType.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CreateMemberServiceTest {

    @Autowired
    private CreateMemberService createMemberService;


    private static Stream<Arguments> getMemberInfo() {
        return Stream.of(
                Arguments.of(
                        new CreateMemberDTO(
                                "1122ss",
                                "name1",
                                Role.MEMBER,
                                "email@test.com",
                                "profileImage",
                                PlatformEnum.GITHUB
                        )
                ),
                Arguments.of(
                        new CreateMemberDTO(
                                "2211ss",
                                "name2",
                                Role.MEMBER,
                                "email@test.com",
                                "profileImage",
                                PlatformEnum.GITHUB
                        )
                )
        );
    }
    @DisplayName("사용자 생성 DTO를 통해 생성이 되는지 확인")
    @ParameterizedTest
    @MethodSource("getMemberInfo")
    void create( CreateMemberDTO createMemberDTO) {

        Assertions.assertDoesNotThrow(
                () -> createMemberService.create(createMemberDTO)
        );
    }
}