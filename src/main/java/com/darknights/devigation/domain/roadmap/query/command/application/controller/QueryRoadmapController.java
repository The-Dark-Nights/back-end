package com.darknights.devigation.domain.roadmap.query.command.application.controller;

import com.darknights.devigation.domain.roadmap.query.command.application.dto.QueryRoadmapDTO;
import com.darknights.devigation.domain.roadmap.query.command.service.QueryRoadmapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roadmap")
public class QueryRoadmapController {
    private final QueryRoadmapService queryRoadmapService;

    @Autowired
    public QueryRoadmapController(QueryRoadmapService queryRoadmapService) {
        this.queryRoadmapService = queryRoadmapService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{roadmapId}")
    public ResponseEntity<?> getRoadmap(@PathVariable Long roadmapId){
        return ResponseEntity.ok(queryRoadmapService.getRoadmapInfo(roadmapId));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<?> getAllRoadmap(){
        return ResponseEntity.ok(queryRoadmapService.getAllRoadmap());
    }
}
