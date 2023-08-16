package com.darknights.devigation.roadmap.command.application.service;

import com.darknights.devigation.roadmap.command.application.dto.CreateRoadmapCategoryDTO;
import com.darknights.devigation.roadmap.command.application.dto.UpdateRoadmapCategoryDTO;
import com.darknights.devigation.roadmap.command.domain.RoadmapRepository.RoadmapCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UpdateRoadmapCategoryService {
    private final CreateRoadmapCategoryService createRoadmapCategoryService;
    private final DeleteRoadmapCategoryService deleteRoadmapCategoryService;
    private final RoadmapCategoryRepository roadmapCategoryRepository;

    @Autowired
    public UpdateRoadmapCategoryService(CreateRoadmapCategoryService createRoadmapCategoryService, DeleteRoadmapCategoryService deleteRoadmapCategoryService, RoadmapCategoryRepository roadmapCategoryRepository) {
        this.createRoadmapCategoryService = createRoadmapCategoryService;
        this.deleteRoadmapCategoryService = deleteRoadmapCategoryService;
        this.roadmapCategoryRepository = roadmapCategoryRepository;
    }

    @Transactional
    public boolean updateRoadmapCategory(long roadmapId, List<CreateRoadmapCategoryDTO> createRoadmapCategoryDTOS) {
        deleteRoadmapCategoryService.deleteRoadmapCategory(roadmapId);
        long updatedRoadmapId=createRoadmapCategoryDTOS.get(0).getRoadmapId();
        for (CreateRoadmapCategoryDTO createRoadmapCategoryDTO : createRoadmapCategoryDTOS) {
            createRoadmapCategoryService.createRoadmapCategory(createRoadmapCategoryDTO);
        }
        return roadmapCategoryRepository.findAllByRoadmapId_Id(roadmapId).isEmpty() && !roadmapCategoryRepository.findAllByRoadmapId_Id(updatedRoadmapId).isEmpty();
    }
}
