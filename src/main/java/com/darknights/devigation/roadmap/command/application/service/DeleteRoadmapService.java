package com.darknights.devigation.roadmap.command.application.service;

import com.darknights.devigation.roadmap.command.domain.RoadmapRepository.RoadmapRepository;
import com.darknights.devigation.roadmap.command.domain.aggregate.entity.Roadmap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteRoadmapService {
    private final RoadmapRepository roadmapRepository;
    @Autowired
    public DeleteRoadmapService(RoadmapRepository roadmapRepository) {
        this.roadmapRepository = roadmapRepository;
    }

    public void deleteRoadmap(long id){
        Optional<Roadmap>roadmap = roadmapRepository.findById(id);
        if(roadmap.isPresent()){
            roadmapRepository.delete(roadmap.get());
        }else{
            //예외 처리
        }
    }

}
