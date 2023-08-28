package com.darknights.devigation.global.filter;

import com.darknights.devigation.global.filter.NullPointExceptionFilter;
import io.jsonwebtoken.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class NullPointExceptionFilterTest {

    private MockHttpServletRequest mockRequest;
    private MockHttpServletResponse mockResponse;
    private MockFilterChain mockFilterChain;
    @Autowired
    private NullPointExceptionFilter nullPointExceptionFilter;

    @BeforeEach
    void setUp() {
        mockRequest = new MockHttpServletRequest();
        mockResponse = new MockHttpServletResponse();
        mockFilterChain = new MockFilterChain();

    }

    @DisplayName("nullPointExceptionFilter 에서 다음 필터로 넘어 가는지 테스트")
    @Test
    void testFilterContinuesTONextFilter() throws ServletException, IOException, java.io.IOException {
        MockFilterChain mockFilterChainSpy = spy(mockFilterChain);
        nullPointExceptionFilter.doFilterInternal(mockRequest, mockResponse, mockFilterChainSpy);
        verify(mockFilterChainSpy, times(1)).doFilter(mockRequest, mockResponse);
    }
}