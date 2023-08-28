package com.darknights.devigation.domain.roadmap.command.domain.RoadmapRepository;

import com.darknights.devigation.domain.roadmap.command.domain.aggregate.entity.Roadmap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoadmapRepository extends JpaRepository<Roadmap,Long> {
}
