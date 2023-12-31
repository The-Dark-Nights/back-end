package com.darknights.devigation.domain.roadmap.command.application.service;

import com.darknights.devigation.domain.roadmap.command.domain.RoadmapRepository.RoadmapRepository;
import com.darknights.devigation.domain.roadmap.command.domain.service.RoadmapService;
import com.darknights.devigation.domain.roadmap.command.application.dto.CreateRoadmapDTO;
import com.darknights.devigation.domain.roadmap.command.domain.aggregate.entity.Roadmap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateRoadmapService {
    private final RoadmapRepository roadmapRepository;
    private final RoadmapService roadmapService;

    @Autowired
    public CreateRoadmapService(RoadmapRepository roadmapRepository, RoadmapService roadmapService) {
        this.roadmapRepository = roadmapRepository;
        this.roadmapService = roadmapService;
    }


    public Roadmap createRoadmap(CreateRoadmapDTO createRoadmapDTO){
        return roadmapRepository.save(roadmapService.toRoadmapEntity(createRoadmapDTO));
    }
}
