package com.darknights.devigation.common.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final HandlerExceptionResolver resolver;

    @Autowired
    public CustomAccessDeniedHandler(@Qualifier("handlerExceptionResolver")  HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//
//        String requestUrl = "/oauth2/authorize/github";
//        final Map<String, Object> body = new HashMap<>();
//        body.put("status", HttpServletResponse.SC_FORBIDDEN);
//        body.put("error", "FORBIDDEN");
//        body.put("message", "API에 접근할 권한이 없습니다.");
//        body.put("path", request.getServletPath());
//        body.put("request_url", requestUrl);
//        final ObjectMapper mapper = new ObjectMapper();
//        mapper.writeValue(response.getOutputStream(), body);
//        try (OutputStream os = response.getOutputStream()) {
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.writeValue(os, mapper);
//            os.flush();
//        }
        resolver.resolveException(request, response, null, accessDeniedException);
    }
}
