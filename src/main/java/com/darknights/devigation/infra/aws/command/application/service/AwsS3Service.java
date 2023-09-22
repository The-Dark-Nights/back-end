package com.darknights.devigation.infra.aws.command.application.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import marvin.image.MarvinImage;
import org.marvinproject.image.transform.scale.Scale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;



@Service
public class AwsS3Service {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3Client amazonS3Client;

    @Autowired
    public AwsS3Service(AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }


    public String uploadImage(MultipartFile multipartFiles) {

        String fileName = createFileName(multipartFiles.getOriginalFilename());
        // UUID 파일명 생성
        String fileFormatName = multipartFiles.getContentType().substring(multipartFiles.getContentType().lastIndexOf("/")+1);

        MultipartFile resizedFile = resizeImage(fileName, fileFormatName, multipartFiles, 768);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        // 객체를 생성해 파일에 대한 메타데이터 설정
        objectMetadata.setContentLength(multipartFiles.getSize());
        objectMetadata.setContentType(multipartFiles.getContentType());
        // Spring Server에서 S3로 파일을 업로드하기 위해
        // 이때의 파일 사이즈와 파일 타입을 알려주기 위해 ObjectMetadata사용

        String url = null;
        try (InputStream inputStream = multipartFiles.getInputStream()) {
            // InputStream을 사용해 파일의 데이터 읽음

            PutObjectRequest uploadFile = new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3Client.putObject(uploadFile);
            // 객체를 생성해 S3에 전송, withCannedAcl => 파일을 공개 읽기 권한으로 설정

            url = String.valueOf(amazonS3Client.getUrl(bucket, uploadFile.getKey()));

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "이미지 업로드에 실패했습니다.");
            // 수정 해당 Exception은 추후@RestControllerAdvice와 Assert에서 통합 관리하면 좋을 거 같습니다
        }
        // S3에 저장된 fileNameList(즉, UUID로 변환된 fileNameList)


        return url;
    }

    private String createFileName(String fileName) {
        try {
            return UUID.randomUUID().toString().concat(getFileExtension(fileName));
            // 파일 이름이 증복되지 않게 하기 위해 랜덤으로 UUID 생성
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일 확장자(" + fileName + ")입니다.");
        }
    }

    private String getFileExtension(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf("."));
        if (extension.equals(".png") || extension.equals(".jpg") || extension.equals(".jpeg") || extension.equals(".gif")) {
            return extension;
        }
        return null;
    }

    public void deleteImage(String fileName) {

        amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, fileName));

    }

    MultipartFile resizeImage(String fileName, String fileFormatName, MultipartFile originalImage, int targetWidth) {
        try {
            BufferedImage image = ImageIO.read(originalImage.getInputStream());

            int originWidth = image.getWidth();
            int originHeight = image.getHeight();

            // 원래 사이즈가 기준 width보다 작을 경우는 resizing 하지 않는다.
            if (originWidth < targetWidth) {
                return originalImage;
            }
            MarvinImage imageMarvin = new MarvinImage(image);

            Scale scale = new Scale();
            scale.load();
            scale.setAttribute("newWidth", targetWidth);
            scale.setAttribute("newHeight", targetWidth * originHeight / originWidth);
            scale.process(imageMarvin.clone(), imageMarvin, null,null, false);

            BufferedImage imageNoAlpha = imageMarvin.getBufferedImageNoAlpha();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(imageNoAlpha, fileFormatName, baos);
            baos.flush();

            return new MockMultipartFile(fileName, baos.toByteArray());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 리사이즈에 실패했습니다.");
        }
    }
}




