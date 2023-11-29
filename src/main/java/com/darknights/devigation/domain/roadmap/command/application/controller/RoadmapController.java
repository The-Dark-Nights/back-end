package com.darknights.devigation.domain.roadmap.command.application.controller;
import com.darknights.devigation.domain.roadmap.command.application.dto.CreateRoadmapDTO;
import com.darknights.devigation.domain.roadmap.command.application.dto.UpdateRoadmapDTO;
import com.darknights.devigation.domain.roadmap.command.application.service.CreateRoadmapService;
import com.darknights.devigation.domain.roadmap.command.application.service.DeleteRoadmapService;
import com.darknights.devigation.domain.roadmap.command.application.service.UpdateRoadmapService;
import com.darknights.devigation.domain.roadmap.command.domain.aggregate.entity.Roadmap;
import com.darknights.devigation.global.common.annotation.CurrentMember;
import com.darknights.devigation.global.security.token.UserPrincipal;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

@RestController
@RequestMapping("/roadmap")
public class RoadmapController {

    private final CreateRoadmapService createRoadmapService;
    private final UpdateRoadmapService updateRoadmapService;
    private final DeleteRoadmapService deleteRoadmapService;


    @Autowired
    public RoadmapController(CreateRoadmapService createRoadmapService, UpdateRoadmapService updateRoadmapService, DeleteRoadmapService deleteRoadmapService) {
        this.createRoadmapService = createRoadmapService;
        this.updateRoadmapService = updateRoadmapService;
        this.deleteRoadmapService = deleteRoadmapService;
    }

//    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<?> createRoadmap(@CurrentMember UserPrincipal userPrincipal, @RequestBody CreateRoadmapDTO createRoadmapDTO){
        createRoadmapDTO.setMemberId(userPrincipal.getId());
        Roadmap result = createRoadmapService.createRoadmap(createRoadmapDTO);

        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<?> updateRoadmap(@CurrentMember UserPrincipal userPrincipal, @RequestBody UpdateRoadmapDTO updateRoadmapDTO){
        updateRoadmapDTO.setMemberId(userPrincipal.getId());
        boolean result = updateRoadmapService.updateRoadmap(updateRoadmapDTO);
        return result?ResponseEntity.ok("update success"):ResponseEntity.status(HttpStatus.BAD_REQUEST).body("update fail");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteRoadmap(@RequestBody Long roadmapId){
        deleteRoadmapService.deleteRoadmap(roadmapId);
        return ResponseEntity.ok("삭제 성공");
    }
}
