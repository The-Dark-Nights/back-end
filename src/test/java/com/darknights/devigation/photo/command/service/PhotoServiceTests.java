package com.darknights.devigation.photo.command.service;

import com.darknights.devigation.photo.command.application.dto.PhotoDTO;
import com.darknights.devigation.photo.command.application.service.InsertPhotoService;
import com.darknights.devigation.photo.command.domain.aggregate.entity.enumtype.PhotoCategory;
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

    private MockMvc mvc;

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
}
