package com.darknights.devigation.post.command.application.service;

import com.darknights.devigation.post.command.application.dto.UpdatePostDTO;
import com.darknights.devigation.post.command.domain.aggregate.entity.Post;
import com.darknights.devigation.post.command.domain.aggregate.vo.CategoryVO;
import com.darknights.devigation.post.command.domain.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class PostUpdateService {
    private final PostRepository postRepository;

    @Autowired
    public PostUpdateService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public boolean updatePost(UpdatePostDTO updatePostDTO) {
        Optional<Post> findPost = postRepository.findById(updatePostDTO.getId());
        if (findPost.isPresent()) {
            Post updatePost = findPost.get();
            // 제목은 null X
            if (updatePostDTO.getTitle() != null && updatePostDTO.getTitle() != "") {
                updatePost.setTitle(updatePostDTO.getTitle());
            }
            // memberId 변화 여부가 없기 때문에 생략
            // 카테고리 null x
            if(updatePostDTO.getCategoryId() != null ){
                CategoryVO categoryId=CategoryVO.builder().categoryId(updatePostDTO.getCategoryId()).build();
                updatePost.setCategoryId(categoryId);
            }
            // content null o => 이전 값과 다를 경우만 값 할당
            if(updatePostDTO.getContent() != findPost.get().getContent()){
                updatePost.setContent(updatePostDTO.getContent());
            }
            // 임시저장 즉, false일 시는 게시한 날짜 할 필요 없으니 생략
            // 즉, 게시글을 수정하고 다시 업로드 할 때만 새로운 시간 부여
            if(updatePostDTO.isPublished() == true){
                updatePost.setCreatedDate(LocalDateTime.now());
            }else{
                updatePost.setPublished(updatePostDTO.isPublished());
            }
            postRepository.save(updatePost);
            return true;
        } else {
            return false;
        }
    }
}