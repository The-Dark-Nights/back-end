package com.darknights.devigation.domain.post.command.application.service;

import com.darknights.devigation.domain.post.command.application.dto.ResponsePostDTO;
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

    public ResponsePostDTO createPost(CreatePostDTO createPostDTO, Long memberIdDTO){
        MemberVO memberId = MemberVO.builder().memberId(memberIdDTO).build();
        CategoryVO categoryId=CategoryVO.builder().categoryId(createPostDTO.getCategoryId()).build();

        Post post = postRepository.save(new Post(createPostDTO.getTitle(),memberId,categoryId, createPostDTO.getContent(),createPostDTO.isPublished()));

        ResponsePostDTO postDTO = new ResponsePostDTO(
                post.getId(),
                post.getTitle(),
                post.getMemberId().getId(),
                post.getCategoryId().getId(),
                post.getContent(),
                post.getCreatedDate(),
                post.isPublished()
        );

        return postDTO;
    }
}
