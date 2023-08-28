package com.darknights.devigation.domain.roadmap.command.application.service;

import com.darknights.devigation.domain.roadmap.command.domain.RoadmapRepository.RoadmapEdgeRepository;
import com.darknights.devigation.domain.roadmap.command.domain.RoadmapRepository.RoadmapNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteRoadmapCategoryService {
    private final RoadmapEdgeRepository roadmapEdgeRepository;
    private final RoadmapNodeRepository roadmapNodeRepository;

    @Autowired
    public DeleteRoadmapCategoryService(RoadmapEdgeRepository roadmapEdgeRepository, RoadmapNodeRepository roadmapNodeRepository) {
        this.roadmapEdgeRepository = roadmapEdgeRepository;
        this.roadmapNodeRepository = roadmapNodeRepository;
    }

    @Transactional
    public boolean deleteRoadmapCategory(long roadmapId) {
        if (roadmapNodeRepository.findAllByRoadmapId_Id(roadmapId).size() > 0) {
            roadmapEdgeRepository.deleteRoadmapEdgesByRoadmapId_Id(roadmapId);
            roadmapNodeRepository.deleteRoadmapNodesByRoadmapId_Id(roadmapId);
            return true;
        }

        return false;
    }
}
