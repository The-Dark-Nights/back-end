package com.darknights.devigation.security.command.domain.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class CustomTokenServiceTest {


    @Value("${app.auth.testExampleToken}")
    private String TEST_EXAMPLE_TOKEN;

    @Autowired
    private CustomTokenService customTokenService;

    @Test
    void testGetUserIdFromToken() {
        Long memberId = customTokenService.getUserIdFromToken(TEST_EXAMPLE_TOKEN);
        Assertions.assertNotNull(memberId);

    }
}