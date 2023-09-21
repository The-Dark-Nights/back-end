package com.darknights.devigation.infra.aws.command.application.service;


import com.darknights.devigation.infra.aws.command.application.service.AwsS3Service;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.nio.charset.StandardCharsets;

@SpringBootTest
public class AwsS3ServiceTests {

//    @Autowired
//    private AwsS3Service awsS3Service;

//    @DisplayName("Aws S3 이미지 업로드 테스트")
//    @Test
//    void uploadImageTest() {
//        final String fileName = "testImage"; //파일명
//        final String contentType = "png"; //파일타입
//
//        MockMultipartFile imgFile = new MockMultipartFile(
//                "images",
//                fileName + "." + contentType,
//                contentType,
//                "images".getBytes(StandardCharsets.UTF_8)
//        );
//        String imgUrl = awsS3Service.uploadImage(imgFile);
//        Assertions.assertNotNull(imgUrl);
//
//        String[] imgLocation = imgUrl.split("/");
//
//        // JPA와 다르게 commit rollback 개념이 존재하지 않는 경우
//        // 직접 접근하여 DB(or Cloud)에서 값을 삭제해야한다.
//        // 실제로 앞으로 테스트를 진행하다보면 transactional이 동작하지 않는 경우가 생길텐데 그럴떈 위와 같이 진행해도 된다.
//        awsS3Service.deleteImage(imgLocation[3]);
//        // 수정 이렇게 되면 Mock을 사용한 이유가 크게 존재하지 않을수도 있음
//    }
}
