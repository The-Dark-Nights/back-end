package com.darknights.devigation.global.configuration;

import com.darknights.devigation.global.filter.AuthenticationExceptionFilter;
import com.darknights.devigation.global.filter.NullPointExceptionFilter;
import com.darknights.devigation.global.filter.TokenAuthenticationFilter;
import com.darknights.devigation.global.handler.CustomAccessDeniedHandler;
import com.darknights.devigation.global.handler.CustomOAuth2FailHandler;
import com.darknights.devigation.global.handler.CustomOAuth2SuccessHandler;
import com.darknights.devigation.domain.member.query.domain.aggregate.entity.enumType.Role;
import com.darknights.devigation.global.security.command.application.service.CustomOAuth2UserService;
import com.darknights.devigation.global.security.command.application.service.CustomUserDetailService;
import com.darknights.devigation.global.security.command.domain.repository.HttpCookieOAuth2AuthorizationRequestRepository;
import com.darknights.devigation.global.security.command.domain.service.CustomTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOAuth2SuccessHandler oAuth2AuthenticationSuccessHandler;
    private final CustomOAuth2FailHandler oAuth2AuthenticationFailureHandler;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final NullPointExceptionFilter nullPointExceptionFilter;

    @Autowired
    @Qualifier("RestAuthenticationEntryPoint")
    private AuthenticationEntryPoint authEntryPoint;

    @Autowired
    private CustomTokenService customTokenService;
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;



    AuthenticationExceptionFilter authenticationExceptionFilter(HandlerExceptionResolver resolver) {
        return new AuthenticationExceptionFilter(resolver);
    }

//    NullPointExceptionFilter nullPointExceptionFilter(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
//        return new NullPointExceptionFilter(resolver);
//    }

    TokenAuthenticationFilter tokenAuthenticationFilter(CustomTokenService customTokenService,
                                                        CustomUserDetailService customUserDetailService) {
        return new TokenAuthenticationFilter(customTokenService, customUserDetailService);
    }

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


//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer(){
//        return web -> {
//            web.ignoring()
//                    .antMatchers(
//                            "/", "/error","/favicon.ico", "/**/*.png",
//                            "/**/*.gif", "/**/*.svg", "/**/*.jpg",
//                            "/**/*.html", "/**/*.css", "/**/*.js"
//                            )
//                    .antMatchers(
//                            "/swagger", "/swagger-ui.html", "/swagger-ui/**",
//                            "/api-docs", "/api-docs/**", "/v3/api-docs/**"
//                    )
//                    .antMatchers(
//                            "/login/**","/auth/**"
//                    );
//        };


    @Bean
    @Order(0)
    public SecurityFilterChain exceptionSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .requestCache().disable()
                .securityContext().disable()
                .sessionManagement().disable()
                .requestMatchers((matchers) ->
                        matchers
                                .antMatchers(
                            "/", "/error","/favicon.ico", "/**/*.png",
                            "/**/*.gif", "/**/*.svg", "/**/*.jpg",
                            "/**/*.html", "/**/*.css", "/**/*.js"
                            )
                            .antMatchers(
                            "/swagger", "/swagger-ui.html", "/swagger-ui/**",
                            "/api-docs", "/api-docs/**", "/v3/api-docs/**"
                            )
                            .antMatchers(
                                "/login/**","/auth/**"
                                    // 여기는 filter를 거치지 않는 곳이기 때문에 여기에 uri를 허용하면 토큰 정보가 받아와지지 않는다.
                            )
                )
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll());

        return http.build();
    }

    @Bean
    @Order(1)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

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
                .authenticationEntryPoint(authEntryPoint)
                .and()

                .authorizeRequests()
                    .antMatchers("/oauth2/**")
                        .hasRole(Role.MEMBER.name())
                    .antMatchers("/blog/**", "/member/**", "/v1/post/**", "/roadmap/**")
                        .permitAll()
                    .antMatchers("/admin/**")
                        .hasRole(Role.ADMIN.name())
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

        http
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler);

        http
                .addFilterBefore(tokenAuthenticationFilter(customTokenService, customUserDetailService), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(authenticationExceptionFilter(resolver), TokenAuthenticationFilter.class)
                .addFilterBefore(nullPointExceptionFilter, AuthenticationExceptionFilter.class);

        return http.build();
    }

}
