package com.darknights.devigation.roadmap.command.domain.RoadmapRepository;

import com.darknights.devigation.roadmap.command.domain.aggregate.entity.RoadmapEdge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoadmapEdgeRepository extends JpaRepository<RoadmapEdge,Long> {
}
