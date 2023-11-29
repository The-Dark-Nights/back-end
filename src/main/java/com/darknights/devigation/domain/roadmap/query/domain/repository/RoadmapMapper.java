package com.darknights.devigation.domain.roadmap.query.domain.repository;

import com.darknights.devigation.domain.roadmap.query.command.application.dto.QueryRoadmapDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoadmapMapper {
    List<QueryRoadmapDTO> findAllRoadmap();
    QueryRoadmapDTO findRoadmapByRoadmapId(long roadmapId);
}
