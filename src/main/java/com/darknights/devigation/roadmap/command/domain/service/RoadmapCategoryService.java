package com.darknights.devigation.roadmap.command.domain.service;

import com.darknights.devigation.common.annotation.DomainService;
import com.darknights.devigation.roadmap.command.application.dto.CreateRoadmapCategoryDTO;
import com.darknights.devigation.roadmap.command.application.dto.UpdateRoadmapCategoryDTO;
import com.darknights.devigation.roadmap.command.domain.aggregate.entity.RoadmapCategory;

@DomainService
public class RoadmapCategoryService {
    public RoadmapCategory toRoadmapCategoryEntity(CreateRoadmapCategoryDTO createRoadmapCategoryDTO) {
        return new RoadmapCategory(createRoadmapCategoryDTO.getCategoryId(), createRoadmapCategoryDTO.getRoadmapId(), createRoadmapCategoryDTO.getPosition(), createRoadmapCategoryDTO.getChildCategoryId(), createRoadmapCategoryDTO.getParentCategoryId());
    }
    public RoadmapCategory toRoadmapCategoryEntity(UpdateRoadmapCategoryDTO updateRoadmapCategoryDTO){
        return new RoadmapCategory(updateRoadmapCategoryDTO.getCategoryId(), updateRoadmapCategoryDTO.getId(),updateRoadmapCategoryDTO.getRoadmapId(), updateRoadmapCategoryDTO.getPosition(), updateRoadmapCategoryDTO.getChildCategoryId(), updateRoadmapCategoryDTO.getParentCategoryId());

    }
}
