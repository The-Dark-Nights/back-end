package com.darknights.devigation.domain.post.command.application.controller;


import com.darknights.devigation.domain.post.command.application.dto.CreatePostDTO;
import com.darknights.devigation.domain.post.command.application.dto.ExceptionDTO;
import com.darknights.devigation.domain.post.command.application.dto.ResponsePostDTO;
import com.darknights.devigation.domain.post.command.application.service.CreatePostService;
import com.darknights.devigation.domain.post.command.application.service.DeletePostService;
import com.darknights.devigation.domain.post.command.application.service.UpdatePostService;
import com.darknights.devigation.global.common.response.api.CustomApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "post", description = "글 API")
@RestController
@RequestMapping("/v1/post")
public class PostController {

    private final CreatePostService createPostService;

    private final UpdatePostService updatePostService;

    private final DeletePostService deletePostService;

    @Autowired
    public PostController(CreatePostService createPostService, UpdatePostService updatePostService, DeletePostService deletePostService) {
        this.createPostService = createPostService;
        this.updatePostService = updatePostService;
        this.deletePostService = deletePostService;
    }

    @Operation(summary = "글 등록", description = "test")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "게시글 저장 성공",
                    content = @Content(
                            schema = @Schema(implementation = ResponsePostDTO.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "게시글 저장 실패",
                    content = @Content(schema = @Schema(implementation = ExceptionDTO.class)))
    })
    @PostMapping("/create")
    public ResponseEntity<?>  createPost(@RequestBody CreatePostDTO createPostDTO) {
        ResponsePostDTO post = createPostService.createPost(createPostDTO);
        CustomApiResponse<ResponsePostDTO> response = new CustomApiResponse<>(HttpStatus.CREATED.value(), "saved successfully", post);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
