package com.darknights.devigation.domain.category.service;


import com.darknights.devigation.domain.category.command.application.dto.CreateCategoryDTO;
import com.darknights.devigation.domain.category.command.application.dto.UpdateCategoryDTO;
import com.darknights.devigation.domain.category.command.application.service.CreateCategoryService;
import com.darknights.devigation.domain.category.command.application.service.DeleteCategoryService;
import com.darknights.devigation.domain.category.command.application.service.UpdateCategoryService;
import com.darknights.devigation.domain.category.command.domain.aggregate.entity.enumtype.Classification;
import com.darknights.devigation.domain.category.command.domain.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@SpringBootTest
@Transactional
public class CategoryServiceTests {

    @Autowired
    private CreateCategoryService createCategoryService;
    @Autowired
    private DeleteCategoryService deleteCategoryService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UpdateCategoryService updateCategoryService;

    private static Stream<Arguments> getCategory() {
        return Stream.of(
                Arguments.of(
                        "TestLanguage",
                        1L
                )
        );
    }

    private static Stream<Arguments> updateCategory() {
        return Stream.of(
                Arguments.of(
                        "TestLanguage",
                        1L,
                        "updatedContents"
                )
        );
    }

    private static Stream<Arguments> getCategoryWithClassification() {
        return Stream.of(
                Arguments.of(
                        "TestLanguage",
                        1L,
                        Classification.CLASS1
                )
        );
    }

    @DisplayName("새로운 카테고리 추가 테스트")
    @ParameterizedTest
    @MethodSource("getCategory")
    void createCategoryTest(String name, Long memberId) {
        CreateCategoryDTO createCategoryDTO = new CreateCategoryDTO(name, memberId);
        Assertions.assertNotNull(createCategoryService.createCategory(createCategoryDTO));
    }

    @DisplayName("카테고리 삭제 테스트")
    @ParameterizedTest
    @MethodSource("getCategory")
    void deleteCategoryTest(String name, Long memberId) {
        CreateCategoryDTO createCategoryDTO = new CreateCategoryDTO(name, memberId);
        long id = createCategoryService.createCategory(createCategoryDTO).getId();
        if (categoryRepository.findById(id).isPresent()) {
            deleteCategoryService.deleteCategory(id);
            Assertions.assertFalse(categoryRepository.findById(id).isPresent());
        } else {
            Assertions.fail();
        }

    }

    @DisplayName("카테고리 업데이트 테스트")
    @ParameterizedTest
    @MethodSource("updateCategory")
    void updateCategoryTest(String name, Long memberId, String newName) {
        CreateCategoryDTO createCategoryDTO = new CreateCategoryDTO(name, memberId);
        long id = createCategoryService.createCategory(createCategoryDTO).getId();
        if (categoryRepository.findById(id).isPresent()) {
            UpdateCategoryDTO updateCategoryDTO = new UpdateCategoryDTO(id, newName, memberId);
            Assertions.assertTrue(updateCategoryService.updateCategory(updateCategoryDTO));
        }else{
            Assertions.fail();
        }
    }

    @DisplayName("대분류 추가 후 카테고리 생성 테스트")
    @ParameterizedTest
    @MethodSource("getCategoryWithClassification")
    void CreateCategoryWithClassification(String name, Long memberId, Classification classification){
        CreateCategoryDTO createCategoryDTO = new CreateCategoryDTO(name,memberId,classification);
        Assertions.assertNotNull(createCategoryService.createCategory(createCategoryDTO));
    }

}

