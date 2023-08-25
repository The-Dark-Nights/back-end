package com.darknights.devigation.roadmap.command.domain.RoadmapRepository;

import com.darknights.devigation.roadmap.command.domain.aggregate.entity.RoadmapEdge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoadmapEdgeRepository extends JpaRepository<RoadmapEdge,Long> {
    void deleteRoadmapEdgesByRoadmapId_Id(long roadmapId);
    List<Optional<RoadmapEdge>> findAllByRoadmapId_Id(long roadmapId);
}
