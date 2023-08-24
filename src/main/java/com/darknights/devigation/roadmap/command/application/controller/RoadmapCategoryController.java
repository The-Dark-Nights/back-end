package com.darknights.devigation.roadmap.command.application.controller;
import com.darknights.devigation.roadmap.command.application.dto.CreateEdgeDTO;
import com.darknights.devigation.roadmap.command.application.dto.CreateNodeDTO;
import com.darknights.devigation.roadmap.command.application.service.CreateRoadmapCategoryService;
import com.darknights.devigation.roadmap.command.domain.service.RoadmapCategoryService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
//@RequestMapping("/")
public class RoadmapCategoryController {



    private final CreateRoadmapCategoryService createRoadmapCategoryService;
    private final RoadmapCategoryService roadmapCategoryService;
    @Autowired
    public RoadmapCategoryController(CreateRoadmapCategoryService createRoadmapCategoryService, RoadmapCategoryService roadmapCategoryService) {
        this.createRoadmapCategoryService = createRoadmapCategoryService;
        this.roadmapCategoryService = roadmapCategoryService;
    }


    @PostMapping(value = "/roadmap")
    public String createRoadmap(@RequestBody HashMap<String,String> param) throws ParseException {
        Pair<List<CreateNodeDTO>,List<CreateEdgeDTO>> info = roadmapCategoryService.jsonToDto(param);
        createRoadmapCategoryService.createRoadmapCategory(info.getFirst(),info.getSecond());

        return "all good";
    }

    @GetMapping("/roadmap")
    public String ReadRoadmap(){
//        for (String key : param.keySet()){
//            System.out.println("key = " + key + "value = " + param.get(key));
//        }

        return "all good";
    }

}
