package com.darknights.devigation.domain.roadmap.command.domain.service;

import com.darknights.devigation.global.common.annotation.DomainService;
import com.darknights.devigation.domain.roadmap.command.application.dto.CreateRoadmapDTO;
import com.darknights.devigation.domain.roadmap.command.application.dto.UpdateRoadmapDTO;
import com.darknights.devigation.domain.roadmap.command.domain.aggregate.entity.Roadmap;

@DomainService
public class RoadmapService {
    public Roadmap toRoadmapEntity(CreateRoadmapDTO createRoadmapDTO){
        return new Roadmap(createRoadmapDTO.getTitle(),createRoadmapDTO.getMemberId());
    }

    public Roadmap toRoadmapEntity(UpdateRoadmapDTO updateRoadmapDTO){
        return new Roadmap(updateRoadmapDTO.getId(), updateRoadmapDTO.getTitle(),updateRoadmapDTO.getMemberId());
    }

}
