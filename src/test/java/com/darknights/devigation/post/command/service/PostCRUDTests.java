package com.darknights.devigation.post.command.service;

import com.darknights.devigation.post.command.application.dto.CreatePostDTO;
import com.darknights.devigation.post.command.application.service.PostCreateService;
import com.darknights.devigation.post.command.application.service.PostDeleteService;
import com.darknights.devigation.post.command.application.service.PostUpdateService;
import com.darknights.devigation.post.command.domain.aggregate.entity.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.stream.Stream;

@SpringBootTest
@Transactional
public class PostCRUDTests {

    @Autowired
    private PostCreateService postCreateService;

    @Autowired
    private PostUpdateService postUpdateService;

    @Autowired
    private PostDeleteService postDeleteService;

    private static Stream<Arguments> createPost(){
        return Stream.of(
                Arguments.of(
                        1L,
                        1L,
                        "피드 작성 테스트입니다."
                )
        );
    }

    @DisplayName("게시글 작성 여부 확인")
    @ParameterizedTest
    @MethodSource("createPost")
    void createPostTest(Long memberId, Long categoryId, String contents){
        CreatePostDTO post=new CreatePostDTO();
        post.setMemberId(memberId);
        post.setCategoryId(categoryId);
        post.setContent(contents);

        Post resultPost =postCreateService.createPost(post);
        System.out.println("resultPost  = " + resultPost );
        Assertions.assertNotNull(resultPost);
    }

//    @DisplayName("게시글 수정 여부 확인")
//    @ParameterizedTest

}
