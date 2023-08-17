package com.darknights.devigation.roadmap.repository;

import com.darknights.devigation.roadmap.command.application.dto.CreateRoadmapCategoryDTO;
import com.darknights.devigation.roadmap.command.application.service.CreateRoadmapCategoryService;
import com.darknights.devigation.roadmap.command.application.service.DeleteRoadmapCategoryService;
import com.darknights.devigation.roadmap.command.domain.RoadmapRepository.RoadmapCategoryRepository;
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
@Transactional
public class RoadmapCategoryRepositoryTests {
    @Autowired
    private RoadmapCategoryRepository roadmapCategoryRepository;
    @Autowired
    private CreateRoadmapCategoryService createRoadmapCategoryService;
    @Autowired
    private RoadmapCategoryMapper roadmapCategoryMapper;

    private static Stream<Arguments> getRoadmapCategory(){
        return Stream.of(
                Arguments.arguments(Arrays.asList(
                        new CreateRoadmapCategoryDTO(1L,1L,"100 200",2L,0L),
                        new CreateRoadmapCategoryDTO(2L,1L,"100 300",3L,1L),
                        new CreateRoadmapCategoryDTO(3L,1L,"100 400",0L,2L)
                ),1L)
        );
    }

    @DisplayName("로드맵 카테고리 매퍼 테스트")
    @ParameterizedTest
    @MethodSource("getRoadmapCategory")
    void RoadmapCategoryMapperTest(List<CreateRoadmapCategoryDTO> createRoadmapCategoryDTOS,Long roadmapId){
        for (CreateRoadmapCategoryDTO createRoadmapCategoryDTO : createRoadmapCategoryDTOS) {
            if (roadmapCategoryRepository.findById(createRoadmapCategoryService.createRoadmapCategory(createRoadmapCategoryDTO).getId()).isEmpty()) {
                Assertions.fail();
            }
        }
        List<QueryRoadmapCategory> queryRoadmapCategories=roadmapCategoryMapper.findRoadmapCategoryByRoadmapId(roadmapId);
        queryRoadmapCategories.forEach(System.out::println);
    }
}
