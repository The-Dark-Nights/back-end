package com.darknights.devigation.domain.post.query.service;

import com.darknights.devigation.domain.post.command.application.dto.CreatePostDTO;
import com.darknights.devigation.domain.post.command.application.dto.ResponsePostDTO;
import com.darknights.devigation.domain.post.command.application.service.CreatePostService;
import com.darknights.devigation.domain.post.command.application.service.DeletePostService;
import com.darknights.devigation.domain.post.command.domain.aggregate.entity.Post;
import com.darknights.devigation.domain.post.query.application.dto.FindPostDTO;
import com.darknights.devigation.domain.post.query.application.service.FindPostService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
public class PostReadTests {

    @Autowired
    private CreatePostService createPostService;

    @Autowired
    private DeletePostService deletePostService;

    @Autowired
    private FindPostService findPostService;

    private static Stream<Arguments> createPost() {
        return Stream.of(
                Arguments.of(
                        new CreatePostDTO(
                                "테스트용 제목 1번, testTitle_1",
                                1L,
                                1L,
                                "테스트용 내용 1번, testContent_1",
                                true
                        ),
                        new CreatePostDTO(
                                "테스트용 제목 2번, testTitle_2",
                                1L,
                                1L,
                                "테스트용 내용 2번, testContent_2",
                                true
                        ),
                        new CreatePostDTO(
                                "테스트용 제목 3번, testTitle_3",
                                2L,
                                1L,
                                "테스트용 내용 3번, testContent_3",
                                true
                        )
                )
        );
    }

    Long postId_1;
    Long postId_2;
    Long postId_3;

    @AfterEach
    void deletePost() {
        if (postId_3 != null) {
            deletePostService.deletePost(postId_1);
            deletePostService.deletePost(postId_2);
            deletePostService.deletePost(postId_3);
        } else {
            deletePostService.deletePost(postId_1);
        }
        postId_1 = null;
        postId_2 = null;
        postId_3 = null;
    }

    @DisplayName("글 전체 조회 테스트")
    @ParameterizedTest
    @MethodSource("createPost")
    void findAllTest(CreatePostDTO postDTO1, CreatePostDTO postDTO2, CreatePostDTO postDTO3) {
        //given
        int beforeSize = findPostService.findAll().size();
        ResponsePostDTO post_1 = createPostService.createPost(postDTO1);
        ResponsePostDTO post_2 = createPostService.createPost(postDTO2);
        ResponsePostDTO post_3 = createPostService.createPost(postDTO3);
        postId_1 = post_1.getId();
        postId_2 = post_2.getId();
        postId_3 = post_3.getId();

        //when
        List<FindPostDTO> posts = findPostService.findAll();
        int afterSize = posts.size();

        //then
        Assertions.assertNotNull(posts);
        Assertions.assertNotEquals(beforeSize, afterSize);
        Assertions.assertEquals(beforeSize + 3, afterSize);


    }

    @DisplayName("memberId 별 글 조회 테스트")
    @ParameterizedTest
    @MethodSource("createPost")
    void findByMemberIdTest(CreatePostDTO postDTO1, CreatePostDTO postDTO2, CreatePostDTO postDTO3){
        //given
        ResponsePostDTO post_1 = createPostService.createPost(postDTO1);
        ResponsePostDTO post_2 = createPostService.createPost(postDTO2);
        ResponsePostDTO post_3 = createPostService.createPost(postDTO3);
        postId_1 = post_1.getId();
        postId_2 = post_2.getId();
        postId_3 = post_3.getId();
        Long member = post_1.getMemberId();
        Long notMember = post_3.getMemberId();
        //when
        List<FindPostDTO> posts= findPostService.findByMemberId(post_1.getMemberId());

        //then
        Assertions.assertNotNull(posts);
        Assertions.assertEquals(member,posts.get(0).getMemberId());
        Assertions.assertNotEquals(notMember,posts.get(0).getMemberId());

    }

    @DisplayName("id로 글 조회 (상세보기) 테스트")
    @ParameterizedTest
    @MethodSource("createPost")
    void findByIdTest(CreatePostDTO postDTO1){
        //given
        ResponsePostDTO post_1 = createPostService.createPost(postDTO1);
        postId_1 = post_1.getId();

        //when
        FindPostDTO post = findPostService.findById(post_1.getId());

        //then
        Assertions.assertNotNull(post);
        Assertions.assertEquals(postId_1, post.getId());

    }

    @DisplayName("제목 및 내용으로 검색 기능 테스트")
    @ParameterizedTest
    @MethodSource("createPost")
    void findByTitle(CreatePostDTO postDTO1){
        //given
        ResponsePostDTO post_1 = createPostService.createPost(postDTO1);
        postId_1 = post_1.getId();
        String searchTitle_kr = "제목 1번";
//        String searchTitle_en = "Title_1";

        String searchContent_kr ="내용 1번";
//        String searchContent_en ="Content_1";
        //when
        List<FindPostDTO> findPost_1 = findPostService.findByTitle(searchTitle_kr);
        List<FindPostDTO> findPost_2 = findPostService.findByContent(searchContent_kr);
        //then
        Assertions.assertNotNull(findPost_1);
        Assertions.assertNotNull(findPost_2);
        Assertions.assertEquals(findPost_1.get(0).getId(), findPost_2.get(0).getId());

    }
}
