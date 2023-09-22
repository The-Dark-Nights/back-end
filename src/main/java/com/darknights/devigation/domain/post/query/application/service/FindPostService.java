package com.darknights.devigation.domain.post.query.application.service;

import com.darknights.devigation.domain.post.query.application.dto.FindPostDTO;
import com.darknights.devigation.domain.post.query.domain.repository.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindPostService {

    private final PostMapper postMapper;

    @Autowired
    public FindPostService(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    public List<FindPostDTO> findAll(){
        return postMapper.findAll();
    }

    public List<FindPostDTO> findByMemberId(Long memberId){

        return postMapper.findByMemberId(memberId);
    }

    public FindPostDTO findById(Long id){
        return postMapper.findById(id);
    }

    public List<FindPostDTO> findByContent(String searchWord){
        return postMapper.findByContent(searchWord);
    }
    public List<FindPostDTO> findByTitle(String searchWord){
        return postMapper.findByTitle(searchWord);
    }

}
