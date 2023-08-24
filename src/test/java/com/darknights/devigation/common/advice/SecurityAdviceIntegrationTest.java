package com.darknights.devigation.common.advice;



import com.darknights.devigation.common.filter.TokenAuthenticationFilter;
import com.darknights.devigation.member.command.domain.aggregate.entity.enumType.PlatformEnum;
import com.darknights.devigation.member.command.domain.aggregate.entity.enumType.Role;
import com.darknights.devigation.member.query.application.dto.FindMemberDTO;
import com.darknights.devigation.member.query.application.service.FindMemberService;
import com.darknights.devigation.security.command.application.service.CustomUserDetailService;
import com.darknights.devigation.security.command.domain.service.CustomTokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
public class SecurityAdviceIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private CustomTokenService customTokenService;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @MockBean
    private FindMemberService findMemberService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private String testToken;

    @BeforeEach
    public void setup() {

        FindMemberDTO mockFindMemberDTO = new FindMemberDTO(
                0L,
                "mockName",
                "email@test.com",
                "profileImage",
                PlatformEnum.GITHUB.name(),
                Role.MEMBER.name()
        );

        when(findMemberService.findById(0L)).thenReturn(mockFindMemberDTO);

        testToken = customTokenService.createToken(mockFindMemberDTO.getId(), mockFindMemberDTO.getRole());

        // mvc
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .addFilter(new TokenAuthenticationFilter(customTokenService, customUserDetailService))
                .build();

    }

    @DisplayName("권한이 없는 사용자가 API 요청 시 Forbidden ErrorResponse를 응답하는지 테스트")
    @Test
    void testAccessDeniedExceptionException() throws Exception {

        mockMvc.perform(
                get("/admin")
                        .header("Authorization", "Bearer " + testToken)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                )
                .andDo(print())
                .andExpectAll(
                       jsonPath("$.status").value(equalTo(403)),
                        jsonPath("$.result.code").value(equalTo("Forbidden")),
                        jsonPath("$.result.classes").value(equalTo("AccessDeniedException"))
                );
    }
}
