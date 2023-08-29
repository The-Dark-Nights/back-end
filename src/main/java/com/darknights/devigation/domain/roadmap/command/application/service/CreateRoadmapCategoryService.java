package com.darknights.devigation.domain.roadmap.command.application.service;

import com.darknights.devigation.domain.roadmap.command.application.dto.CreateEdgeDTO;
import com.darknights.devigation.domain.roadmap.command.application.dto.CreateNodeDTO;
import com.darknights.devigation.domain.roadmap.command.domain.RoadmapRepository.RoadmapEdgeRepository;
import com.darknights.devigation.domain.roadmap.command.domain.RoadmapRepository.RoadmapNodeRepository;
import com.darknights.devigation.domain.roadmap.command.domain.aggregate.entity.RoadmapEdge;
import com.darknights.devigation.domain.roadmap.command.domain.aggregate.entity.RoadmapNode;
import com.darknights.devigation.domain.roadmap.command.domain.service.RoadmapCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CreateRoadmapCategoryService {
    private final RoadmapNodeRepository roadmapNodeRepository;
    private final RoadmapEdgeRepository roadmapEdgeRepository;
    private final RoadmapCategoryService roadmapCategoryService;

    @Autowired
    public CreateRoadmapCategoryService(RoadmapNodeRepository roadmapNodeRepository, RoadmapEdgeRepository roadmapEdgeRepository, RoadmapCategoryService roadmapCategoryService) {
        this.roadmapNodeRepository = roadmapNodeRepository;
        this.roadmapEdgeRepository = roadmapEdgeRepository;
        this.roadmapCategoryService = roadmapCategoryService;
    }


    @Transactional
    public boolean createRoadmapCategory(List<CreateNodeDTO> createNodeDTOS, List<CreateEdgeDTO> createEdgeDTOS) {
        if (createNodeDTOS.size() > 0) {
            for (CreateNodeDTO createNodeDTO : createNodeDTOS) {
                RoadmapNode roadmapNode = roadmapCategoryService.toRoadmapNode(createNodeDTO);
                roadmapNodeRepository.save(roadmapNode);
            }
            if (createEdgeDTOS.size() > 0) {
                for (CreateEdgeDTO createEdgeDTO : createEdgeDTOS) {
                    RoadmapEdge roadmapEdge = roadmapCategoryService.toRoadmapEdge(createEdgeDTO);
                    roadmapEdgeRepository.save(roadmapEdge);
                }
            }
            return true;
        }
        return false;
    }
}
