package com.darknights.devigation.roadmap.command.application.service;

import com.darknights.devigation.roadmap.command.domain.RoadmapRepository.RoadmapEdgeRepository;
import com.darknights.devigation.roadmap.command.domain.RoadmapRepository.RoadmapNodeRepository;
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
    public void deleteRoadmapCategory(long roadmapId){
        roadmapEdgeRepository.deleteRoadmapEdgesByRoadmapId_Id(roadmapId);
        roadmapNodeRepository.deleteRoadmapNodesByRoadmapId_Id(roadmapId);
    }
}
