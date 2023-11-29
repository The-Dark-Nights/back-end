package com.darknights.devigation.domain.roadmap.command.application.service;

import com.darknights.devigation.domain.roadmap.command.domain.RoadmapRepository.RoadmapRepository;
import com.darknights.devigation.domain.roadmap.command.domain.service.RoadmapService;
import com.darknights.devigation.domain.roadmap.command.application.dto.UpdateRoadmapDTO;
import com.darknights.devigation.domain.roadmap.command.domain.aggregate.entity.Roadmap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class    UpdateRoadmapService {
    private final RoadmapRepository roadmapRepository;
    private final RoadmapService roadmapService;

    @Autowired
    public UpdateRoadmapService(RoadmapRepository roadmapRepository, RoadmapService roadmapService) {
        this.roadmapRepository = roadmapRepository;
        this.roadmapService = roadmapService;
    }

    @Transactional
    public boolean updateRoadmap(UpdateRoadmapDTO updateRoadmapDTO){
        Optional<Roadmap> roadmap = roadmapRepository.findById(updateRoadmapDTO.getId());
        if(roadmap.isPresent()){
            Roadmap updateRoadmap = roadmap.get();
            if(!updateRoadmapDTO.getTitle().isEmpty()){
                updateRoadmap.setTitle(updateRoadmapDTO.getTitle());
            }
            if(!updateRoadmapDTO.getRoadmap().isEmpty()){
                updateRoadmap.setRoadmap(updateRoadmapDTO.getRoadmap());
            }

            updateRoadmap.setModifiedDate(LocalDateTime.now());
            return true;
        }else{
            return false;
        }
    }
}
