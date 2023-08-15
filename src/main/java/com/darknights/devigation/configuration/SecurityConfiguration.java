package com.darknights.devigation.configuration;

import com.darknights.devigation.common.filter.JwtExceptionFilter;
import com.darknights.devigation.common.filter.TokenAuthenticationFilter;
import com.darknights.devigation.common.handler.CustomOAuth2FailHandler;
import com.darknights.devigation.common.handler.CustomOAuth2SuccessHandler;
import com.darknights.devigation.security.command.application.service.CustomOAuth2UserService;
import com.darknights.devigation.security.command.application.service.RestAuthenticationEntryPoint;
import com.darknights.devigation.security.command.domain.repository.HttpCookieOAuth2AuthorizationRequestRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOAuth2SuccessHandler oAuth2AuthenticationSuccessHandler;
    private final CustomOAuth2FailHandler oAuth2AuthenticationFailureHandler;
    private final TokenAuthenticationFilter tokenAuthenticationFilter;
    private final JwtExceptionFilter jwtExceptionFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
      기본적으로 Spring OAuth2는 HttpSessionOAuth2AuthorizationRequestRepository를 사용하여 인증 요청을 저장합니다.
      그러나 우리 서비스는 상태 비저장이므로 세션에 저장할 수 없습니다.
      대신 Base64로 인코딩된 쿠키에 요청을 저장합니다.
    */
    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtExceptionFilter, tokenAuthenticationFilter.getClass());

        http
                .cors()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf()
                .disable()
                .formLogin()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .and()

                .authorizeRequests()
                    .antMatchers("/", "/error","/favicon.ico", "/**/*.png", "/**/*.gif", "/**/*.svg", "/**/*.jpg", "/**/*.html", "/**/*.css", "/**/*.js")
                        .permitAll()
                    .antMatchers("/swagger", "/swagger-ui.html", "/swagger-ui/**", "/api-docs", "/api-docs/**", "/v3/api-docs/**")
                        .permitAll()
                    .antMatchers("/login/**","/auth/**", "/oauth2/**")
                        .permitAll()
                    .antMatchers("/blog/**")
                        .permitAll()
                    .anyRequest()
                        .authenticated()
                    .and()
                .oauth2Login()
                    .authorizationEndpoint()
                        .baseUri("/oauth2/authorize")
                    .authorizationRequestRepository(cookieAuthorizationRequestRepository())
                    .and()
                    .redirectionEndpoint()
                        .baseUri("/oauth2/callback/*")
                        .and()
                    .userInfoEndpoint()
                        .userService(customOAuth2UserService)
                        .and()
                    .successHandler(oAuth2AuthenticationSuccessHandler)
                    .failureHandler(oAuth2AuthenticationFailureHandler);

        return http.build();
    }

}
