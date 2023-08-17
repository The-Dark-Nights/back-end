package com.darknights.devigation.roadmap.query.domain.repository;

import com.darknights.devigation.roadmap.query.domain.aggregate.entity.QueryRoadmapCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoadmapCategoryMapper {
    List<QueryRoadmapCategory> findRoadmapCategoryByRoadmapId(long roadmapId);
}
