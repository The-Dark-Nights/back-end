package com.darknights.devigation.common.advice;

import static org.junit.jupiter.api.Assertions.*;

import com.darknights.devigation.common.entity.error.ErrorResponse;
import com.darknights.devigation.security.command.domain.service.CustomTokenService;
import com.darknights.devigation.security.command.domain.exception.OAuth2AuthenticationProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthControllerAdviceUnitTest {

    @Autowired
    private AuthControllerAdvice authControllerAdvice;

    @Autowired
    private CustomTokenService customTokenService;

    @DisplayName("토큰이 없을 경우 정상적으로 401 상태 코드를 응답하는지 테스트")
    @Test
    public void testHandleAuthenticationExceptionReturn401() {

        String token = "";

        OAuth2AuthenticationProcessingException exception = assertThrows(OAuth2AuthenticationProcessingException.class, () -> customTokenService.validateToken(token));
        ResponseEntity<ErrorResponse> responseEntity = authControllerAdvice.handleAuthenticationException(exception);
        assertEquals(401, responseEntity.getStatusCodeValue());
    }
}