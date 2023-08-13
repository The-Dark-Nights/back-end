package com.darknights.devigation.roadmap.service;


import com.darknights.devigation.roadmap.command.application.dto.CreateRoadmapDTO;
import com.darknights.devigation.roadmap.command.application.dto.UpdateRoadmapDTO;
import com.darknights.devigation.roadmap.command.application.service.CreateRoadmapService;
import com.darknights.devigation.roadmap.command.application.service.DeleteRoadmapService;
import com.darknights.devigation.roadmap.command.application.service.UpdateRoadmapService;
import com.darknights.devigation.roadmap.command.domain.RoadmapRepository.RoadmapRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@Transactional
@SpringBootTest
public class RoadmapServiceTests {
    @Autowired
    private CreateRoadmapService createRoadmapService;
    @Autowired
    private DeleteRoadmapService deleteRoadmapService;
    @Autowired
    private UpdateRoadmapService updateRoadmapService;
    @Autowired
    private RoadmapRepository roadmapRepository;

    private static Stream<Arguments> getRoadmap(){
        return Stream.of(
                Arguments.of(
                        "Title",
                        1L
                )
        );
    }
    private static Stream<Arguments> updateRoadmap(){
        return Stream.of(
                Arguments.of(
                        "Title",
                        1L,
                        "updatedTitle"
                )
        );
    }

    @DisplayName("로드맵 추가 테스트")
    @ParameterizedTest
    @MethodSource("getRoadmap")
    void createRoadmapTest(String title, Long memberId){
        CreateRoadmapDTO createRoadmapDTO = new CreateRoadmapDTO(title,memberId);
        Assertions.assertNotNull(createRoadmapService.createRoadmap(createRoadmapDTO));
    }

    @DisplayName("로드맵 삭제 테스트")
    @ParameterizedTest
    @MethodSource("getRoadmap")
    void deleteRoadmapTest(String title, Long memberId){
        CreateRoadmapDTO createRoadmapDTO = new CreateRoadmapDTO(title,memberId);
        Long id  = createRoadmapService.createRoadmap(createRoadmapDTO).getId();
        if(roadmapRepository.findById(id).isPresent()){
            deleteRoadmapService.deleteRoadmap(id);
            Assertions.assertFalse(roadmapRepository.findById(id).isPresent());
        }else{
            Assertions.fail("존재하지 않음");
        }
    }

    @DisplayName("로드맵 업데이트 테스트")
    @ParameterizedTest
    @MethodSource("updateRoadmap")
    void updateRoadmapTest(String title, Long memberId, String updatedTitle){
        CreateRoadmapDTO createRoadmapDTO = new CreateRoadmapDTO(title,memberId);
        long id = createRoadmapService.createRoadmap(createRoadmapDTO).getId();
        if(roadmapRepository.findById(id).isPresent()){
            UpdateRoadmapDTO updateRoadmapDTO  = new UpdateRoadmapDTO(id, updatedTitle,memberId);
            Assertions.assertTrue(updateRoadmapService.updateRoadmap(updateRoadmapDTO));
            System.out.println( roadmapRepository.findById(id).get());
        }else{
            Assertions.fail();
        }
    }
}
