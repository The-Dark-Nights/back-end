package com.darknights.devigation.domain.post.command.application.service;

import com.darknights.devigation.domain.post.command.domain.aggregate.entity.Post;
import com.darknights.devigation.domain.post.command.domain.aggregate.vo.CategoryVO;
import com.darknights.devigation.domain.post.command.domain.aggregate.vo.MemberVO;
import com.darknights.devigation.domain.post.command.application.dto.CreatePostDTO;
import com.darknights.devigation.domain.post.command.domain.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CreatePostService {
    private final PostRepository postRepository;

    @Autowired
    public CreatePostService(PostRepository postRepository){
        this.postRepository=postRepository;
    }

    public Post createPost(CreatePostDTO createPostDTO){
        MemberVO memberId = MemberVO.builder().memberId(createPostDTO.getMemberId()).build();
        CategoryVO categoryId=CategoryVO.builder().categoryId(createPostDTO.getCategoryId()).build();

        return postRepository.save(new Post(createPostDTO.getTitle(),memberId,categoryId, createPostDTO.getContent(),createPostDTO.isPublished()));
    }
}