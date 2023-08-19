package com.darknights.devigation.security.command.application.controller;


import com.darknights.devigation.common.entity.api.ApiResponse;
import com.darknights.devigation.security.command.application.dto.AuthResponse;
import com.darknights.devigation.security.command.application.dto.AuthResponseBody;
import com.darknights.devigation.security.command.application.service.IssueTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final IssueTokenService issueTokenService;

    @Autowired
    public AuthController(IssueTokenService issueTokenService) {
        this.issueTokenService = issueTokenService;
    }

    @PostMapping("token")
    public ResponseEntity<?> issueToken (@RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken) {

        String accessToken =  bearerToken.substring(7);

        String issuedToken = issueTokenService.issueTokenByAccessToken(accessToken);

        ApiResponse apiResponse = new ApiResponse()
                .setStatus(HttpStatus.FORBIDDEN.value())
                .setMessage("새로운 Access Token을 발급했습니다.")
                .setTimestamp(LocalDateTime.now());


        AuthResponseBody authResponseBody = new AuthResponseBody()
                .setAccessToken(issuedToken);

        AuthResponse authResponse = new AuthResponse()
                .setApiResponse(apiResponse)
                .setBody(authResponseBody);
        return ResponseEntity.status(HttpStatus.CREATED).body(authResponse);
    }
}
