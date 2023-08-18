package com.darknights.devigation.common.advice;


import com.darknights.devigation.common.entity.api.ApiResponse;
import com.darknights.devigation.common.entity.error.ErrorResponseBody;
import com.darknights.devigation.common.entity.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class AuthControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ AuthenticationException.class })
    public ResponseEntity<ErrorResponse> handleAuthenticationException(Exception ex) {

        ErrorResponseBody errorResponseBody = new ErrorResponseBody()
                .setCode(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .setClasses(ex.getClass().getSimpleName());

        ApiResponse apiResponse = new ApiResponse()
                .setStatus(HttpStatus.UNAUTHORIZED.value())
                .setMessage(ex.getLocalizedMessage())
                .setTimestamp(LocalDateTime.now());


        ErrorResponse errorResponse = new ErrorResponse()
                .setApiResponse(apiResponse)
                .setResult(errorResponseBody);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(Exception ex) {

        ErrorResponseBody errorResponseBody = new ErrorResponseBody()
                .setCode(HttpStatus.FORBIDDEN.getReasonPhrase())
                .setClasses(ex.getClass().getSimpleName());

        ApiResponse apiResponse = new ApiResponse()
                .setStatus(HttpStatus.FORBIDDEN.value())
                .setMessage("API에 접근할 권한이 없습니다.")
                .setTimestamp(LocalDateTime.now());

        ErrorResponse errorResponse = new ErrorResponse()
                .setApiResponse(apiResponse)
                .setResult(errorResponseBody);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }
}
