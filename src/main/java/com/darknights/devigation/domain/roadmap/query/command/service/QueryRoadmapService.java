package com.darknights.devigation.domain.roadmap.query.command.service;

import com.darknights.devigation.domain.roadmap.query.command.application.dto.QueryRoadmapDTO;
import com.darknights.devigation.domain.roadmap.query.domain.repository.RoadmapMapper;
import com.darknights.devigation.domain.roadmap.query.domain.service.FindMemberServiceInRoadmap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryRoadmapService {
    private final RoadmapMapper roadmapMapper;
    private final FindMemberServiceInRoadmap findMemberServiceInRoadmap;

    @Autowired
    public QueryRoadmapService(RoadmapMapper roadmapMapper, FindMemberServiceInRoadmap findMemberServiceInRoadmap) {
        this.roadmapMapper = roadmapMapper;
        this.findMemberServiceInRoadmap = findMemberServiceInRoadmap;
    }

    public QueryRoadmapDTO getRoadmapInfo(Long roadmapId){
        QueryRoadmapDTO roadmapDTO =  roadmapMapper.findRoadmapByRoadmapId(roadmapId);
        roadmapDTO.setFindMemberDTO(findMemberServiceInRoadmap.FindMember(roadmapDTO.getMemberId()));
        return roadmapDTO;
    }

    public List<QueryRoadmapDTO> getAllRoadmap(){
        List<QueryRoadmapDTO> roadmapDTOS = roadmapMapper.findAllRoadmap();
        for (QueryRoadmapDTO roadmap:roadmapDTOS
             ) {
            roadmap.setFindMemberDTO(findMemberServiceInRoadmap.FindMember(roadmap.getMemberId()));
        }
        return roadmapDTOS;
    }
}
