package com.darknights.devigation.roadmap.command.domain.service;

import com.darknights.devigation.common.annotation.DomainService;
import com.darknights.devigation.roadmap.command.application.dto.CreateEdgeDTO;
import com.darknights.devigation.roadmap.command.application.dto.CreateNodeDTO;
import com.darknights.devigation.roadmap.command.domain.aggregate.entity.RoadmapEdge;
import com.darknights.devigation.roadmap.command.domain.aggregate.entity.RoadmapNode;

@DomainService
public class RoadmapCategoryService {

    public RoadmapNode toRoadmapNode(CreateNodeDTO createNodeDTO){
        return new RoadmapNode(createNodeDTO.getRoadmapId(), createNodeDTO.getCategoryId(), createNodeDTO.getPosition());
    }

    public RoadmapEdge toRoadmapEdge(CreateEdgeDTO createEdgeDTO){
        return new RoadmapEdge(createEdgeDTO.getRoadmapId(), createEdgeDTO.getEdgeId(), createEdgeDTO.getSourceCategory(), createEdgeDTO.getTargetCategory());
    }
}
