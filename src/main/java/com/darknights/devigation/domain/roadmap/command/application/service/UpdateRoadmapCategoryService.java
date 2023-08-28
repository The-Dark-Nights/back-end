package com.darknights.devigation.domain.roadmap.command.application.service;

import com.darknights.devigation.domain.roadmap.command.application.dto.CreateEdgeDTO;
import com.darknights.devigation.domain.roadmap.command.application.dto.CreateNodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UpdateRoadmapCategoryService {
    private final CreateRoadmapCategoryService createRoadmapCategoryService;
    private final DeleteRoadmapCategoryService deleteRoadmapCategoryService;


    @Autowired
    public UpdateRoadmapCategoryService(CreateRoadmapCategoryService createRoadmapCategoryService, DeleteRoadmapCategoryService deleteRoadmapCategoryService) {
        this.createRoadmapCategoryService = createRoadmapCategoryService;
        this.deleteRoadmapCategoryService = deleteRoadmapCategoryService;
    }

    @Transactional
    public boolean updateRoadmapCategory(List<CreateNodeDTO> createNodeDTOS, List<CreateEdgeDTO> createEdgeDTOS, long roadmapId){
        if(deleteRoadmapCategoryService.deleteRoadmapCategory(roadmapId)){
            if(createRoadmapCategoryService.createRoadmapCategory(createNodeDTOS,createEdgeDTOS))return true;
        }
        return true;
    }
}