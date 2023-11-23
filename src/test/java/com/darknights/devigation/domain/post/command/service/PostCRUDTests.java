//package com.darknights.devigation.domain.post.command.service;
//
//import com.darknights.devigation.domain.post.command.application.dto.CreatePostDTO;
//import com.darknights.devigation.domain.post.command.application.dto.ResponsePostDTO;
//import com.darknights.devigation.domain.post.command.application.dto.UpdatePostDTO;
//import com.darknights.devigation.domain.post.command.application.service.CreatePostService;
//import com.darknights.devigation.domain.post.command.application.service.DeletePostService;
//import com.darknights.devigation.domain.post.command.application.service.UpdatePostService;
//import com.darknights.devigation.domain.post.command.domain.aggregate.entity.Post;
//import com.darknights.devigation.domain.post.command.domain.repository.PostRepository;
//import com.darknights.devigation.domain.post.query.application.service.FindPostService;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.MethodSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Stream;
//
//@SpringBootTest
//@Transactional
//public class PostCRUDTests {
//
//    @Autowired
//    private CreatePostService createPostService;
//
//    @Autowired
//    private UpdatePostService updatePostService;
//
//    @Autowired
//    private DeletePostService postDeleteService;
//
//
//    @Autowired
//    private PostRepository postRepository;
//
//    private static Stream<Arguments> createPost() {
//        return Stream.of(
//                Arguments.of(
//                        "게시글 제목입니다.",
//                        1L,
//                        1L,
//                        "게시글 작성 내용입니다.",
//                        true
//                )
//        );
//    }
//
//    //    @DisplayName("게시글 수정 여부 확인")
////    @ParameterizedTest
////    @MethodSource("createPost")
////    void updatePostTest(String title, Long memberId, Long categoryId, String contents, boolean published) {
////        CreatePostDTO post = new CreatePostDTO();
////        post.setTitle(title);
////        post.setMemberId(memberId);
////        post.setCategoryId(categoryId);
////        post.setContent(contents);
////        post.setPublished(published);
////
////        // 질문 여기서 updatePostDTO를 수정했는데 createPost까지 바뀌는 이유는???
////        // 영속성 컨텍스트가 정확히 어떻게 작동되는지 이해하지 못하겠다. 동일성 보장의 문제인가?? + 이 메소드에서 commit이 되는 시점은???
////        // DTO는 앤티티 객체가 아니야! 즉 entity객체인 createPost를 update과정에서 가져오기 때문에 값이 바뀌었던 것!!!!
////        Post createPost = createPostService.createPost(post);
////        System.out.println("수정 전 createPost = " + createPost);
////        UpdatePostDTO updatePostDTO=new UpdatePostDTO(
////                createPost.getId(),
////                createPost.getTitle(),
////                createPost.getMemberId(),
////                createPost.getCategoryId(),
////                createPost.getContent(),
////                createPost.getCreatedDate(),
////                createPost.isPublished()
////        );
////        updatePostDTO.setTitle("수정된 게시글 제목입니다.");
////        updatePostService.updatePost(updatePostDTO);
////        Optional<Post> updatePost=postRepository.findById(updatePostDTO.getId());
////        System.out.println("createPost = " + createPost);
////        System.out.println("updatePost = " + updatePost);
//////        Assertions.assertNotEquals(createPost.getTitle(), updatePost.get().getTitle());
////    }
//
//    @DisplayName("게시글 작성 여부 확인")
//    @ParameterizedTest
//    @MethodSource("createPost")
//    void createPostTest(String title, Long memberId, Long categoryId, String contents, boolean published) {
//        CreatePostDTO post = new CreatePostDTO();
//        post.setTitle(title);
//        post.setMemberId(memberId);
//        post.setCategoryId(categoryId);
//        post.setContent(contents);
//        post.setPublished(published);
//
//        ResponsePostDTO resultPost = createPostService.createPost(post);
//        // 삭제
//        System.out.println("resultPost = " + resultPost);
//        Assertions.assertNotNull(resultPost);
//        // 필기 db에는 저장되지 않지만 아이디는 계속 증가한다.
//        // a 왜?????? 이미 commit이 되었기 때문이다. (commit => rollback)
//    }
//    @DisplayName("게시글 수정 제목 검증 여부 확인")
//    @ParameterizedTest
//    @MethodSource("createPost")
//    void updateTitlePostTest(String title, Long memberId, Long categoryId, String contents, boolean published) {
//        // 수정하기 위한 post 저장
//        CreatePostDTO post = new CreatePostDTO();
//        post.setTitle(title);
//        post.setMemberId(memberId);
//        post.setCategoryId(categoryId);
//        post.setContent(contents);
//        post.setPublished(published);
//
//        ResponsePostDTO createPost = createPostService.createPost(post);
//        // 수정전 값을 updatePostDTO에 담아서 update 메소드에 매개변수로 전달
//        UpdatePostDTO updatePostDTO = new UpdatePostDTO(
//                createPost.getId(),
//                createPost.getTitle(),
//                createPost.getMemberId(),
//                createPost.getCategoryId(),
//                createPost.getContent(),
//                createPost.isPublished()
//        );
//        // title을 null값 혹은 빈 문자열로 전송 후 이전과 같은 지 확인
//        String modifyTitle="";
////        String modifyTitle=null;
//        updatePostDTO.setTitle(modifyTitle);
//        updatePostService.updatePost(updatePostDTO);
//        Optional<Post> updatePost = postRepository.findById(updatePostDTO.getId());
//        Assertions.assertNotEquals(modifyTitle, updatePost.get().getTitle());
//    }
//
//    @DisplayName("게시글 수정 내용 검증 여부 확인")
//    @ParameterizedTest
//    @MethodSource("createPost")
//    void updateContentPostTest(String title, Long memberId, Long categoryId, String contents, boolean published) {
//        // 수정하기 위한 post 저장
//        CreatePostDTO post = new CreatePostDTO();
//        post.setTitle(title);
//        post.setMemberId(memberId);
//        post.setCategoryId(categoryId);
//        post.setContent(contents);
//        post.setPublished(published);
//
//        ResponsePostDTO createPost = createPostService.createPost(post);
//        // 수정전 값을 updatePostDTO에 담아서 update 메소드에 매개변수로 전달
//        UpdatePostDTO updatePostDTO = new UpdatePostDTO(
//                createPost.getId(),
//                createPost.getTitle(),
//                createPost.getMemberId(),
//                createPost.getCategoryId(),
//                createPost.getContent(),
//                createPost.isPublished()
//        );
//        // content를 다르게 한 후 값이 바뀌는지 확인
//        String modifyContent="게시물 내용이 수정되었습니다.";
//        updatePostDTO.setContent(modifyContent);
//        updatePostService.updatePost(updatePostDTO);
//        Optional<Post> updatePost = postRepository.findById(updatePostDTO.getId());
//        System.out.println("updatePost = " + updatePost);
//        Assertions.assertEquals(modifyContent, updatePost.get().getContent());
//    }
//
//    @DisplayName("게시글 수정 임시저장 검증 여부 확인")
//    @ParameterizedTest
//    @MethodSource("createPost")
//    void updatePublishedPostTest(String title, Long memberId, Long categoryId, String contents, boolean published) {
//        // 수정하기 위한 post 저장
//        CreatePostDTO post = new CreatePostDTO();
//        post.setTitle(title);
//        post.setMemberId(memberId);
//        post.setCategoryId(categoryId);
//        post.setContent(contents);
//        post.setPublished(published);
//
//        ResponsePostDTO createPost = createPostService.createPost(post);
//        // 수정전 값을 updatePostDTO에 담아서 update 메소드에 매개변수로 전달
//        UpdatePostDTO updatePostDTO = new UpdatePostDTO(
//                createPost.getId(),
//                createPost.getTitle(),
//                createPost.getMemberId(),
//                createPost.getCategoryId(),
//                createPost.getContent(),
//                createPost.isPublished()
//        );
//        // published를 false로 바꾼 후 게시날짜가 바뀌지 않는 것 확인
//        LocalDateTime modifyTime=createPost.getCreatedDate();
//        updatePostDTO.setPublished(false);
//        updatePostService.updatePost(updatePostDTO);
//        Optional<Post> updatePost = postRepository.findById(updatePostDTO.getId());
//        Assertions.assertEquals(modifyTime, updatePost.get().getCreatedDate());
//    }
//
//    @DisplayName("게시글 삭제 여부 확인")
//    @ParameterizedTest
//    @MethodSource("createPost")
//    void deletePostTest(String title, Long memberId, Long categoryId, String contents, boolean published){
//        CreatePostDTO post = new CreatePostDTO();
//        post.setTitle(title);
//        post.setMemberId(memberId);
//        post.setCategoryId(categoryId);
//        post.setContent(contents);
//        post.setPublished(published);
//
//        ResponsePostDTO createPost = createPostService.createPost(post);
//
//        int deleteBeforeCount= (int) postRepository.count();
//        postDeleteService.deletePost(createPost.getId());
//        Assertions.assertEquals(postRepository.count(),deleteBeforeCount-1);
//    }
//
//
//}
