package com.darknights.devigation.post.command.application.service;

import com.darknights.devigation.post.command.domain.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PostDeleteService {
    private final PostRepository postRepository;

    @Autowired
    public PostDeleteService(PostRepository postRepository){
        this.postRepository=postRepository;
    }


    public void deletePost(long postId){
        postRepository.deleteById(postId);
        // 추가 좋아요 삭제 기능
    }
}
