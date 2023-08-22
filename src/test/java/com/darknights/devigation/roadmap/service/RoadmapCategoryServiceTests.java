package com.darknights.devigation.roadmap.service;

import com.darknights.devigation.roadmap.command.application.dto.CreateEdgeDTO;
import com.darknights.devigation.roadmap.command.application.dto.CreateNodeDTO;
import com.darknights.devigation.roadmap.command.application.service.CreateRoadmapCategoryService;
import com.darknights.devigation.roadmap.command.domain.RoadmapRepository.RoadmapNodeRepository;
import com.darknights.devigation.roadmap.query.domain.repository.RoadmapCategoryMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
public class RoadmapCategoryServiceTests {

    @Autowired
    private CreateRoadmapCategoryService createRoadmapCategoryService;

    private static Stream<Arguments> getRoadmapCategory() {
        return Stream.of(
                Arguments.arguments(Arrays.asList(
                                new CreateNodeDTO(1, 1, "100 200"),
                                new CreateNodeDTO(1, 2, "100 300"),
                                new CreateNodeDTO(1, 3, "100 400")
                        ),
                        Arrays.asList(
                                new CreateEdgeDTO(1, "edge 1", 1, 2),
                                new CreateEdgeDTO(1, "edge 2", 2, 3)

                        )

                )
        );
    }


    @ParameterizedTest
    @DisplayName("로드맵 카테고리 생성 테스트")
    @MethodSource("getRoadmapCategory")
    public void CreateRoadmapCategoryTest(List<CreateNodeDTO> createNodeDTOS, List<CreateEdgeDTO> createEdgeDTOS) {
        Assertions.assertTrue(createRoadmapCategoryService.createRoadmapCategory(createNodeDTOS,createEdgeDTOS));
    }
}
