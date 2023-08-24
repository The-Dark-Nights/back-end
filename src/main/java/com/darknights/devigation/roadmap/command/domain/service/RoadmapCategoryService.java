package com.darknights.devigation.roadmap.command.domain.service;

import com.darknights.devigation.common.annotation.DomainService;
import com.darknights.devigation.roadmap.command.application.dto.CreateEdgeDTO;
import com.darknights.devigation.roadmap.command.application.dto.CreateNodeDTO;
import com.darknights.devigation.roadmap.command.domain.aggregate.entity.RoadmapEdge;
import com.darknights.devigation.roadmap.command.domain.aggregate.entity.RoadmapNode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@DomainService
public class RoadmapCategoryService {

    public RoadmapNode toRoadmapNode(CreateNodeDTO createNodeDTO){
        return new RoadmapNode(createNodeDTO.getRoadmapId(), createNodeDTO.getCategoryId(), createNodeDTO.getPosition());
    }

    public RoadmapEdge toRoadmapEdge(CreateEdgeDTO createEdgeDTO){
        return new RoadmapEdge(createEdgeDTO.getRoadmapId(), createEdgeDTO.getEdgeId(), createEdgeDTO.getSourceCategory(), createEdgeDTO.getTargetCategory());
    }

    public Pair<List<CreateNodeDTO>,List<CreateEdgeDTO>> jsonToDto(HashMap<String,String> param) throws ParseException {
        List<CreateNodeDTO> createNodeDTOS = new ArrayList<>();
        List<CreateEdgeDTO> createEdgeDTOS = new ArrayList<>();

        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(param.get("roadmap"));


        JSONArray nodeInfos = (JSONArray) object.get("nodes");
        long roadmapId =Long.parseLong (param.get("roadmapId"));

        for (Object nodeInfo : nodeInfos) {
            JSONObject node = (JSONObject) nodeInfo;
            long categoryId = Long.parseLong((String) node.get("id"));
            String position = getPosition((JSONObject) node.get("position"));
            createNodeDTOS.add(new CreateNodeDTO(roadmapId, categoryId, position));
        }

        JSONArray edgeInfos = (JSONArray) object.get("edges");
        for (Object edgeInfo : edgeInfos) {
            JSONObject edge = (JSONObject) edgeInfo;
            String edgeId = (String) edge.get("id");
            Long sourceId = Long.parseLong((String) edge.get("source"));
            Long targetId = Long.parseLong((String) edge.get("target"));
            createEdgeDTOS.add(new CreateEdgeDTO(roadmapId, edgeId, sourceId, targetId));
        }

        createNodeDTOS.forEach(System.out::println);
        createEdgeDTOS.forEach(System.out::println);

        return Pair.of(createNodeDTOS, createEdgeDTOS);
    }

    public String getPosition(JSONObject position){
        String x = String.valueOf(position.get("x"));
        String y = String.valueOf(position.get("y"));
        return x + " " + y;
    }
}
