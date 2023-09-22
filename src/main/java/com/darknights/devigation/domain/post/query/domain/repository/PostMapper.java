package com.darknights.devigation.domain.post.query.domain.repository;

import com.darknights.devigation.domain.post.query.application.dto.FindPostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    List<FindPostDTO> findAll();

    // memberId별 조회 ( 마이페이지 - 발행여부 상관 없음)
    List<FindPostDTO> findByMemberId(Long memberId);

    //상세조회
    FindPostDTO findById(Long id);

    //내용 검색 기능
    List<FindPostDTO> findByContent(String searchWord);

    // 제목 검색 기능
    List<FindPostDTO> findByTitle(String searchWord);
}
