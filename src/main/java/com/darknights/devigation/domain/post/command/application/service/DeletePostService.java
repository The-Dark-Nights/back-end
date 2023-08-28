package com.darknights.devigation.domain.post.command.application.service;

import com.darknights.devigation.domain.post.command.domain.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletePostService {
    private final PostRepository postRepository;

    @Autowired
    public DeletePostService(PostRepository postRepository){
        this.postRepository=postRepository;
    }


    public void deletePost(Long postId){
        postRepository.deleteById(postId);
        // 추가 좋아요 삭제 기능
    }
}
