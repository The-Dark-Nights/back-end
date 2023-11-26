package com.darknights.devigation.domain.post.query.application.controller;


import com.darknights.devigation.domain.post.query.application.dto.FindPostDTO;
import com.darknights.devigation.domain.post.query.application.service.FindPostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "query post", description = "글 조회 API")
@RestController
@RequestMapping("/v1/post")
public class FindPostController {

    private final FindPostService findPostService;

    @Autowired
    public FindPostController(FindPostService findPostService) {
        this.findPostService = findPostService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<FindPostDTO>> findAll(){
        List<FindPostDTO> posts = findPostService.findAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindPostDTO> findById(@PathVariable Long id){
        FindPostDTO post = findPostService.findById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }


}
