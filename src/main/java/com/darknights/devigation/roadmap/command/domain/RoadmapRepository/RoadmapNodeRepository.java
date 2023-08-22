package com.darknights.devigation.roadmap.command.domain.RoadmapRepository;

import com.darknights.devigation.roadmap.command.domain.aggregate.entity.RoadmapNode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoadmapNodeRepository extends JpaRepository<RoadmapNode,Long> {
    List<Optional<RoadmapNode>> findAllByRoadmapId_Id(long roadmapId);
    Optional<RoadmapNode> findRoadmapCategoryByRoadmapId_IdAndCategoryId_Id(long roadmapId, long categoryId);
}
