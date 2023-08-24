package com.darknights.devigation.common.advice;

import com.darknights.devigation.common.entity.error.ErrorResponse;
import com.darknights.devigation.security.command.domain.exception.OAuth2AuthenticationProcessingException;
import com.darknights.devigation.security.command.domain.exception.UserNotFoundException;
import com.darknights.devigation.security.command.domain.service.CustomTokenService;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.servlet.NoHandlerFoundException;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
/*
* @SpringBootTest를 RANDOM_PORT나 DEFINED_PORT로 사용하면 별도의 쓰레드에서 스프링 컨테이너가 실행된다.
* 테스트가 끝나고 이를 롤백시키려면 하나의 트랜잭션으로 묶여야 하는데,
* 스프링 컨테이너가 실제로 구동되어 테스트와 다른 쓰레드에서 실행되니 하나의 트랜잭션으로 묶일 수 없는 것이다.
* 그래서 @SpringBootTest를 RANDOM_PORT나 DEFINED_PORT로 사용하면 @Transactional을 사용해도 롤백되지 않는다.
* */
public class AuthControllerAdviceIntegrationTest {

    /*
     * TestRestTemplate은 REST 방식으로 개발한 API의 Test를 최적화 하기 위해 만들어진 클래스이다.
     * HTTP 요청 후 데이터를 응답 받을 수 있는 템플릿 객체이며 ResponseEntity와 함께 자주 사용된다.
     * Header와 Content-Type 등을 설정하여 API를 호출 할 수 있다.
     * */

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomTokenService customTokenService;


    @DisplayName("존재하지 않는 url 요청시 NOT_FOUND ErrorResponse를 응답하는지 테스트")
    @Test
    void testThrowNullPointerException() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request =
                new HttpEntity<>(headers);
        ResponseEntity<ErrorResponse> errorResponse = restTemplate.exchange("/auth",HttpMethod.GET,request, ErrorResponse.class);
        System.out.println("errorResponse.getBody().getApiResponse().getTimestamp() = " + errorResponse.getBody().getApiResponse().getTimestamp());
        Assertions.assertThat(errorResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        Assertions.assertThat(errorResponse.getBody().getResult().getClasses()).isEqualTo(NoHandlerFoundException.class.getSimpleName());
    }

    @DisplayName("유효하지 않는 Http 메소드 요청시 METHOD_NOT_ALLOWED ErrorResponse를 응답하는지 테스트")
    @Test
    void testHttpRequestMethodNotSupportedException() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request =
                new HttpEntity<>(headers);
        ResponseEntity<ErrorResponse> errorResponse = restTemplate.exchange("/auth/token",HttpMethod.GET,request, ErrorResponse.class);
        Assertions.assertThat(errorResponse.getStatusCode()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);
        Assertions.assertThat(errorResponse.getBody().getResult().getClasses()).isEqualTo(HttpRequestMethodNotSupportedException.class.getSimpleName());
    }

    @DisplayName("Security를 통하지 않고 헤더에 필요한 값이 없을 경우 BAD_REQUEST ErrorResponse를 응답하는지 테스트")
    @Test
    void testMissingRequestHeaderException() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request =
                new HttpEntity<>("", headers);
        ResponseEntity<ErrorResponse> errorResponse = restTemplate.postForEntity("/auth/token",request, ErrorResponse.class);
        Assertions.assertThat(errorResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        Assertions.assertThat(errorResponse.getBody().getResult().getClasses()).isEqualTo(MissingRequestHeaderException.class.getSimpleName());
    }

    @DisplayName("Authorization에 JWT 토큰이 없을 경우 UNAUTHORIZED ErrorResponse를 응답하는지 테스트")
    @Test
    void testNullTokenAuthenticationException() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request =
                new HttpEntity<>(headers);

        ResponseEntity<ErrorResponse> errorResponse = restTemplate.exchange("/admin/", HttpMethod.GET,request,ErrorResponse.class);
        Assertions.assertThat(errorResponse.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        Assertions.assertThat(errorResponse.getBody().getResult().getClasses()).isEqualTo(OAuth2AuthenticationProcessingException.class.getSimpleName());
        Assertions.assertThat(errorResponse.getBody().getApiResponse().getMessage()).isEqualTo("JWT 토큰이 존재하지 않습니다.");
    }

    @DisplayName("Authorization에 잘못된 형식의 JWT 토큰이 입력 시 UNAUTHORIZED ErrorResponse를 응답하는지 테스트")
    @Test
    void testUnValidAuthenticationException() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer test");
        HttpEntity<String> request =
                new HttpEntity<>(headers);

        ResponseEntity<ErrorResponse> errorResponse = restTemplate.exchange("/admin/", HttpMethod.GET,request,ErrorResponse.class);
        Assertions.assertThat(errorResponse.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        Assertions.assertThat(errorResponse.getBody().getResult().getClasses()).isEqualTo(OAuth2AuthenticationProcessingException.class.getSimpleName());
        Assertions.assertThat(errorResponse.getBody().getApiResponse().getMessage()).isEqualTo("잘못된 JWT 서명");
    }

    @DisplayName("존재하지 않는 사용자 Id의 토큰으로 요청 시 UNAUTHORIZED ErrorResponse를 응답하는지 테스트")
    @Test
    void testUserNotFoundException() {
        String testToken = customTokenService.createToken(0L, "MEMBER");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + testToken);
        HttpEntity<String> request =
                new HttpEntity<>(headers);

        ResponseEntity<ErrorResponse> errorResponse = restTemplate.exchange("/admin", HttpMethod.GET,request,ErrorResponse.class);
        Assertions.assertThat(errorResponse.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        Assertions.assertThat(errorResponse.getBody().getResult().getClasses()).isEqualTo(UserNotFoundException.class.getSimpleName());
        Assertions.assertThat(errorResponse.getBody().getApiResponse().getMessage()).isEqualTo("해당 Id를 가진 사용자를 찾을 수 없습니다.");
    }
}
