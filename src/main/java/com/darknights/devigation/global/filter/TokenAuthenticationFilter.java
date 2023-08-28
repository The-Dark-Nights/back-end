package com.darknights.devigation.global.filter;

import com.darknights.devigation.global.security.command.application.service.CustomUserDetailService;
import com.darknights.devigation.global.security.command.domain.exception.OAuth2AuthenticationProcessingException;
import com.darknights.devigation.global.security.command.domain.service.CustomTokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final CustomTokenService customTokenService;

    private final CustomUserDetailService customUserDetailService;

    public TokenAuthenticationFilter(CustomTokenService customTokenService,
                                     CustomUserDetailService customUserDetailService) {
        this.customTokenService = customTokenService;
        this.customUserDetailService = customUserDetailService;
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = getJwtFromRequest(request);
        if (StringUtils.hasText(jwt) && customTokenService.validateToken(jwt)) {
            Long userId = customTokenService.getUserIdFromToken(jwt);
            UserDetails userDetails = customUserDetailService.loadUserById(userId);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        } else {
            throw new OAuth2AuthenticationProcessingException("JWT 토큰이 존재하지 않습니다.");
        }
    }
}
