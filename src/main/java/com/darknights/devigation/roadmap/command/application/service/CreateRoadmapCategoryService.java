package com.darknights.devigation.roadmap.command.application.service;

import com.darknights.devigation.roadmap.command.application.dto.CreateRoadmapCategoryDTO;
import com.darknights.devigation.roadmap.command.domain.RoadmapRepository.RoadmapCategoryRepository;
import com.darknights.devigation.roadmap.command.domain.aggregate.entity.RoadmapCategory;
import com.darknights.devigation.roadmap.command.domain.service.RoadmapCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateRoadmapCategoryService {
    private final RoadmapCategoryRepository roadmapCategoryRepository;
    private final RoadmapCategoryService roadmapCategoryService;

    @Autowired
    public CreateRoadmapCategoryService(RoadmapCategoryRepository roadmapCategoryRepository, RoadmapCategoryService roadmapCategoryService) {
        this.roadmapCategoryRepository = roadmapCategoryRepository;
        this.roadmapCategoryService = roadmapCategoryService;
    }

    public RoadmapCategory createRoadmapCategory(CreateRoadmapCategoryDTO createRoadmapCategoryDTO){
        return roadmapCategoryRepository.save(roadmapCategoryService.toRoadmapCategoryEntity(createRoadmapCategoryDTO));
    }
}
