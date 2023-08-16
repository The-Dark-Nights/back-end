package com.darknights.devigation.roadmap.command.domain.RoadmapRepository;

import com.darknights.devigation.roadmap.command.domain.aggregate.entity.RoadmapCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoadmapCategoryRepository extends JpaRepository<RoadmapCategory,Long> {
    List<Optional<RoadmapCategory>> findAllByRoadmapId_Id(long roadmapId);
    void deleteAllByRoadmapId_Id(long roadmapId);
}
