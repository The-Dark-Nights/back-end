package com.darknights.devigation.roadmap.command.application.service;

import com.darknights.devigation.roadmap.command.domain.RoadmapRepository.RoadmapCategoryRepository;
import com.darknights.devigation.roadmap.command.domain.aggregate.entity.RoadmapCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DeleteRoadmapCategoryService {
    private final RoadmapCategoryRepository roadmapCategoryRepository;

    @Autowired
    public DeleteRoadmapCategoryService(RoadmapCategoryRepository roadmapCategoryRepository) {
        this.roadmapCategoryRepository = roadmapCategoryRepository;
    }

    @Transactional
    public void deleteRoadmapCategory(Long roadmapId) {
        List<Optional<RoadmapCategory>> roadmapCategories = roadmapCategoryRepository.findAllByRoadmapId_Id(roadmapId);
        if (!roadmapCategories.isEmpty()) {
            for (Optional<RoadmapCategory> roadmapCategory : roadmapCategories) {
                roadmapCategory.ifPresent(roadmapCategoryRepository::delete);
            }
        }
    }
}
