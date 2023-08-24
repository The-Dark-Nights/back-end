package com.darknights.devigation.aws.command.application.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.darknights.devigation.config.AwsS3MockConfig;
import io.findify.s3mock.S3Mock;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;

import java.nio.charset.StandardCharsets;

@Import(AwsS3MockConfig.class)
// @Configuration 선언된 스프링 설정 클래스 가져오는 것
@SpringBootTest
public class AwsS3ServiceTests {

    @Autowired
    private AwsS3Service awsS3Service;

    @Autowired
    private S3Mock s3Mock;

    @Autowired
    private AmazonS3 amazonS3;
    @AfterEach
    public void tearDown() {
        amazonS3.shutdown();
        s3Mock.stop();
    }

    @DisplayName("Aws S3 이미지 업로드 테스트")
    @Test
    void uploadImageTest() {
        final String fileName = "testImage"; //파일명
        final String contentType = "png"; //파일타입

        MockMultipartFile imgFile = new MockMultipartFile(
                "images",
                fileName + "." + contentType,
                contentType,
                "images".getBytes(StandardCharsets.UTF_8)
        );
        String imgUrl = awsS3Service.uploadImage(imgFile);
        Assertions.assertNotNull(imgUrl);

        String[] imgLocation = imgUrl.split("/");

        // JPA와 다르게 commit rollback 개념이 존재하지 않는 경우
        // 직접 접근하여 DB(or Cloud)에서 값을 삭제해야한다.
        // 실제로 앞으로 테스트를 진행하다보면 transactional이 동작하지 않는 경우가 생길텐데 그럴떈 위와 같이 진행해도 된다.
        awsS3Service.deleteImage(imgLocation[3]);
        // 수정 이렇게 되면 Mock을 사용한 이유가 크게 존재하지 않을수도 있음
    }
}
