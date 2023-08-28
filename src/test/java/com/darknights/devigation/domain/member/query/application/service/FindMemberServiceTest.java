package com.darknights.devigation.domain.member.query.application.service;

import com.darknights.devigation.domain.member.command.application.dto.CreateMemberDTO;
import com.darknights.devigation.domain.member.command.application.service.CreateMemberService;
import com.darknights.devigation.domain.member.command.domain.aggregate.entity.Member;
import com.darknights.devigation.domain.member.command.domain.aggregate.entity.enumType.PlatformEnum;
import com.darknights.devigation.domain.member.command.domain.aggregate.entity.enumType.Role;
import com.darknights.devigation.domain.member.query.application.service.FindMemberService;
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
class FindMemberServiceTest {

    @Autowired
    private CreateMemberService createMemberService;

    @Autowired
    private FindMemberService findMemberService;

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
                )
        );
    }

    @DisplayName("UID를 통해 생성이 되는지 확인")
    @ParameterizedTest
    @MethodSource("getMemberInfo")
    void findByUID(CreateMemberDTO createMemberDTO) {
        createMemberService.create(createMemberDTO);

        Assertions.assertNotNull(findMemberService.findByUID(createMemberDTO.getUID()));
    }


    @DisplayName("Id를 통해 생성이 되는지 확인")
    @ParameterizedTest
    @MethodSource("getMemberInfo")
    void findById(CreateMemberDTO createMemberDTO) {
        Member createdMember = createMemberService.create(createMemberDTO);
        Assertions.assertNotNull(findMemberService.findById(createdMember.getId()));
    }
}