package com.darknights.devigation.global.handler.post;

import com.darknights.devigation.domain.post.command.application.dto.ExceptionDTO;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = {"com.darknights.devigation.domain.post"})
public class PostControllerHandler {

    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<ExceptionDTO> PropertyValueException(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionDTO(400,"작성자와 카테고리 데이터가 필요합니다."));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionDTO> IllegalArgumentException(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionDTO(400,"식별자(postId or Id)가 필요합니다."));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDTO> RuntimeException(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionDTO(400,"삭제하려는 데이터가 없습니다."));
    }
}
