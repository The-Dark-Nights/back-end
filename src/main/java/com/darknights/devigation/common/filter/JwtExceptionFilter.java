package com.darknights.devigation.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtExceptionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response); // TokenAuthenticationFilter 이동
        } catch (JwtException ex) {
            // TokenAuthenticationFilter 예외 발생하면 바로 setErrorResponse 호출
            System.out.println("setErrorResponse 호출");
            setErrorResponse(request, response, ex);
        }
    }

    private void setErrorResponse(HttpServletRequest request, HttpServletResponse response, JwtException ex) throws IOException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        // response 상태 코드를 401로 설정
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        final Map<String, Object> body = new HashMap<>();
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        body.put("error", "Unauthorized");
        // ex.getMessage() 에는 jwtException을 발생시키면서 입력한 메세지가 들어있다.
        body.put("message", ex.getMessage());
        body.put("path", request.getServletPath());
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
    }
}
