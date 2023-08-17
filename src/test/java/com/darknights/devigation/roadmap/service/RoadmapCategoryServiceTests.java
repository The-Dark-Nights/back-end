package com.darknights.devigation.roadmap.service;

import com.darknights.devigation.roadmap.command.application.dto.CreateRoadmapCategoryDTO;
import com.darknights.devigation.roadmap.command.application.service.CreateRoadmapCategoryService;
import com.darknights.devigation.roadmap.command.application.service.DeleteRoadmapCategoryService;
import com.darknights.devigation.roadmap.command.application.service.UpdateRoadmapCategoryService;
import com.darknights.devigation.roadmap.command.domain.RoadmapRepository.RoadmapCategoryRepository;
import com.darknights.devigation.roadmap.command.domain.service.RoadmapCategoryService;
import com.darknights.devigation.roadmap.query.domain.aggregate.entity.QueryRoadmapCategory;
import com.darknights.devigation.roadmap.query.domain.repository.RoadmapCategoryMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
public class RoadmapCategoryServiceTests {
    @Autowired
    private RoadmapCategoryRepository roadmapCategoryRepository;
    @Autowired
    private CreateRoadmapCategoryService createRoadmapCategoryService;
    @Autowired
    private DeleteRoadmapCategoryService deleteRoadmapCategoryService;
    @Autowired
    private RoadmapCategoryMapper roadmapCategoryMapper;
    @Autowired
    private UpdateRoadmapCategoryService updateRoadmapCategoryService;

    private static Stream<Arguments> getRoadmapCategory() {
        return Stream.of(
                Arguments.arguments(Arrays.asList(
                        new CreateRoadmapCategoryDTO(1, 1, "100 200", 2, 0),
                        new CreateRoadmapCategoryDTO(2, 1, "100 300", 3, 1),
                        new CreateRoadmapCategoryDTO(3, 1, "100 400", 0, 2)
                ), 1)
        );
    }

    private static Stream<Arguments> updateRoadmapCategory() {
        return Stream.of(
                Arguments.arguments(Arrays.asList(
                                new CreateRoadmapCategoryDTO(1, 1, "100 200", 2, 0),
                                new CreateRoadmapCategoryDTO(2, 1, "100 300", 3, 1),
                                new CreateRoadmapCategoryDTO(3, 1, "100 400", 0, 2)
                        ), 1,
                        Arrays.asList(
                                new CreateRoadmapCategoryDTO(1, 2, "100 250", 2, 0),
                                new CreateRoadmapCategoryDTO(2, 2, "100 350", 3, 1),
                                new CreateRoadmapCategoryDTO(3, 2, "100 450", 0, 2)
                        ))
        );
    }

    @DisplayName("로드맵 카테고리 생성 테스트")
    @ParameterizedTest
    @MethodSource("getRoadmapCategory")
    @Transactional
    void createRoadmapCategoryTest(List<CreateRoadmapCategoryDTO> createRoadmapCategoryDTOS) {
        for (CreateRoadmapCategoryDTO createRoadmapCategoryDTO : createRoadmapCategoryDTOS) {
            if (roadmapCategoryRepository.findById(createRoadmapCategoryService.createRoadmapCategory(createRoadmapCategoryDTO).getId()).isEmpty()) {
                Assertions.fail();
            }
        }
        Assertions.assertTrue(true);
    }

    @DisplayName("로드맵 카테고리 삭제 테스트")
    @ParameterizedTest
    @MethodSource("getRoadmapCategory")
    void deleteRoadmapCategoryTest(List<CreateRoadmapCategoryDTO> createRoadmapCategoryDTOS, long roadmapId) {
        for (CreateRoadmapCategoryDTO createRoadmapCategoryDTO : createRoadmapCategoryDTOS) {
            if (roadmapCategoryRepository.findById(createRoadmapCategoryService.createRoadmapCategory(createRoadmapCategoryDTO).getId()).isEmpty()) {
                Assertions.fail();
            }
        }
        deleteRoadmapCategoryService.deleteRoadmapCategory(roadmapId);

        List<QueryRoadmapCategory> queryRoadmapCategories = roadmapCategoryMapper.findRoadmapCategoryByRoadmapId(roadmapId);
        System.out.println(queryRoadmapCategories.isEmpty());
        queryRoadmapCategories.forEach(System.out::println);
        Assertions.assertTrue(queryRoadmapCategories.isEmpty());

    }

    @DisplayName("로드맵 카테고리 수정 테스트")
    @ParameterizedTest
    @MethodSource("updateRoadmapCategory")
    void updateRoadmapCategoryTest(List<CreateRoadmapCategoryDTO> createRoadmapCategoryDTOS, long roadmapId,List<CreateRoadmapCategoryDTO> updateRoadmapCategoryDTOS){
        for (CreateRoadmapCategoryDTO createRoadmapCategoryDTO : createRoadmapCategoryDTOS) {
            if (roadmapCategoryRepository.findById(createRoadmapCategoryService.createRoadmapCategory(createRoadmapCategoryDTO).getId()).isEmpty()) {
                Assertions.fail();
            }
        }
        Assertions.assertTrue(updateRoadmapCategoryService.updateRoadmapCategory(roadmapId,updateRoadmapCategoryDTOS));
    }
}
