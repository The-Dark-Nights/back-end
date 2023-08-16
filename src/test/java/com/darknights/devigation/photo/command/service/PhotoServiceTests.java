package com.darknights.devigation.photo.command.service;

import com.darknights.devigation.photo.command.application.dto.PhotoDTO;
import com.darknights.devigation.photo.command.application.service.DeletePhotoService;
import com.darknights.devigation.photo.command.application.service.InsertPhotoService;
import com.darknights.devigation.photo.command.application.service.UpdatePhotoService;
import com.darknights.devigation.photo.command.domain.aggregate.entity.enumtype.PhotoCategory;
import com.darknights.devigation.photo.command.domain.repository.PhotoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@SpringBootTest
@Transactional
public class PhotoServiceTests {


    @Autowired
    private InsertPhotoService insertPhotoService;

    @Autowired
    private UpdatePhotoService updatePhotoService;

    @Autowired
    private DeletePhotoService deletePhotoService;

    @Autowired
    private PhotoRepository photoRepository;


    @DisplayName("사진 업로드 테스트 ")
    @Test
    void insertPhotoTest() throws IOException {
        final String fileName = "testImage1"; //파일명
        final String contentType = "png"; //파일타입

        MockMultipartFile imgFile = new MockMultipartFile(
                "images",
                fileName + "." + contentType, //originalFilename
                contentType,
                "images".getBytes(StandardCharsets.UTF_8)
        );
        Long originId= 1L;
        PhotoCategory category= PhotoCategory.POST;

        PhotoDTO resultPhoto=insertPhotoService.insertPhoto(originId,imgFile,category);
        System.out.println("resultPhoto = " + resultPhoto);

        Assertions.assertNotNull(resultPhoto);
    }

    @DisplayName("사진 업데이트 테스트")
    @Test
    void updatePhotoTest() throws IOException {
        final String fileName = "testImage"; //파일명
        final String contentType = "png"; //파일타입

        MockMultipartFile imgFile = new MockMultipartFile(
                "images",
                fileName + "." + contentType, //originalFilename
                contentType,
                "images".getBytes(StandardCharsets.UTF_8)
        );
        Long originId= 1L;
        PhotoCategory category= PhotoCategory.POST;

        PhotoDTO beforePhoto=insertPhotoService.insertPhoto(originId,imgFile,category);


        final String modifyFileName = "modifyImage"; //파일명

        // 새로운 이미지 파일로 update
        MockMultipartFile modifyImgFile = new MockMultipartFile(
                "images",
                modifyFileName + "." + contentType, //originalFilename
                contentType,
                "images".getBytes(StandardCharsets.UTF_8)
        );
        
       PhotoDTO afterPhoto= updatePhotoService.updatePhoto(beforePhoto.getId(), originId,modifyImgFile,category);

       // 다른 이름을 가지고 있는지 확인 => 경로 또한 검증가능하다.
       Assertions.assertNotEquals(beforePhoto.getPhotoRename(), afterPhoto.getPhotoRename());
    }
    @DisplayName("사진 삭제 테스트")
    @Test
    void deletePhotoTest() throws IOException {
        final String fileName = "testImage"; //파일명
        final String contentType = "png"; //파일타입

        MockMultipartFile imgFile = new MockMultipartFile(
                "images",
                fileName + "." + contentType, //originalFilename
                contentType,
                "images".getBytes(StandardCharsets.UTF_8)
        );
        Long originId= 1L;
        PhotoCategory category= PhotoCategory.POST;

        PhotoDTO beforePhoto=insertPhotoService.insertPhoto(originId,imgFile,category);

        int deleteBeforeCount= (int) photoRepository.count();
        deletePhotoService.deletePhoto(beforePhoto.getId());
        Assertions.assertEquals(photoRepository.count(), deleteBeforeCount-1);
    }
}
