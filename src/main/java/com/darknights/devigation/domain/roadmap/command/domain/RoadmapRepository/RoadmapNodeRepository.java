package com.darknights.devigation.domain.roadmap.command.domain.RoadmapRepository;

import com.darknights.devigation.domain.roadmap.command.domain.aggregate.entity.RoadmapNode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoadmapNodeRepository extends JpaRepository<RoadmapNode,Long> {
    void deleteRoadmapNodesByRoadmapId_Id(long roadmapId);
    List<Optional<RoadmapNode>> findAllByRoadmapId_Id(long roadmapId);
}