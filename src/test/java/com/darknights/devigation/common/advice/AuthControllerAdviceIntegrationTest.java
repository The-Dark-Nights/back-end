package com.darknights.devigation.common.advice;

import com.darknights.devigation.common.entity.error.ErrorResponse;
import com.darknights.devigation.member.command.application.dto.CreateMemberDTO;
import com.darknights.devigation.member.command.application.service.CreateMemberService;
import com.darknights.devigation.member.command.domain.aggregate.entity.Member;
import com.darknights.devigation.member.command.domain.aggregate.entity.enumType.PlatformEnum;
import com.darknights.devigation.member.command.domain.aggregate.entity.enumType.Role;
import com.darknights.devigation.member.command.domain.repository.MemberRepository;
import com.darknights.devigation.security.command.domain.exception.OAuth2AuthenticationProcessingException;
import com.darknights.devigation.security.command.domain.exception.UserNotFoundException;
import com.darknights.devigation.security.command.domain.service.CustomTokenService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;
import java.util.stream.Stream;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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

    @Autowired
    private CreateMemberService createMemberService;

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("존재하지 않는 url 요청시 NOT_FOUND ErrorResponse를 응답하는지 테스트")
    @Test
    void testThrowNullPointerException() {
        ResponseEntity<ErrorResponse> errorResponse = restTemplate.getForEntity("/auth", ErrorResponse.class);
        Assertions.assertThat(errorResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        Assertions.assertThat(errorResponse.getBody().getResult().getClasses()).isEqualTo(NoHandlerFoundException.class.getSimpleName());
    }

    @DisplayName("유효하지 않는 Http 메소드 요청시 METHOD_NOT_ALLOWED ErrorResponse를 응답하는지 테스트")
    @Test
    void testHttpRequestMethodNotSupportedException() {
        ResponseEntity<ErrorResponse> errorResponse = restTemplate.getForEntity("/auth/token", ErrorResponse.class);
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

    private static Stream<Arguments> getMemberInfo() {
        return Stream.of(
                Arguments.of(
                        new CreateMemberDTO(
                                "1122ss",
                                "testMember",
                                Role.MEMBER,
                                "email@test.com",
                                "profileImage",
                                PlatformEnum.GITHUB
                        )
                )
        );
    }
    @DisplayName("권한이 없는 사용자가 API 요청 시 Forbidden ErrorResponse를 응답하는지 테스트")
    @ParameterizedTest
    @MethodSource("getMemberInfo")
    void testAccessDeniedExceptionException(CreateMemberDTO createMemberDTO) {
        Member member = createMemberService.create(createMemberDTO);
        String testToken = customTokenService.createToken(member.getId(), member.getRole().name());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + testToken);
        HttpEntity<String> request =
                new HttpEntity<>(headers);

        ResponseEntity<ErrorResponse> errorResponse = restTemplate.exchange("/admin/", HttpMethod.GET,request,ErrorResponse.class);
        memberRepository.delete(member);
        Assertions.assertThat(errorResponse.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
        Assertions.assertThat(errorResponse.getBody().getResult().getClasses()).isEqualTo(AccessDeniedException.class.getSimpleName());
        Assertions.assertThat(errorResponse.getBody().getApiResponse().getMessage()).isEqualTo("API에 접근할 권한이 없습니다.");
    }
}
