package com.darknights.devigation.aws.command.application.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class AwsS3Service {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Autowired
    private final AmazonS3Client amazonS3Client ;

    @Autowired
    public AwsS3Service (AmazonS3Client amazonS3Client){
        this.amazonS3Client = amazonS3Client;
    }


    public String uploadImage(MultipartFile multipartFiles) {

        String fileName = createFileName(multipartFiles.getOriginalFilename());
        // UUID 파일명 생성
        ObjectMetadata objectMetadata = new ObjectMetadata();
        // 객체를 생성해 파일에 대한 메타데이터 설정
        objectMetadata.setContentLength(multipartFiles.getSize());
        objectMetadata.setContentType(multipartFiles.getContentType());
        // Spring Server에서 S3로 파일을 업로드하기 위해
        // 이때의 파일 사이즈와 파일 타입을 알려주기 위해 ObjectMetadata사용

        String url=null;
        try (InputStream inputStream = multipartFiles.getInputStream()) {
            // InputStream을 사용해 파일의 데이터 읽음

            PutObjectRequest uploadFile = new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3Client.putObject(uploadFile);
            // 객체를 생성해 S3에 전송, withCannedAcl => 파일을 공개 읽기 권한으로 설정

            url = String.valueOf(amazonS3Client.getUrl(bucket,uploadFile.getKey()));

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "이미지 업로드에 실패했습니다.");
        }
        // S3에 저장된 fileNameList(즉, UUID로 변환된 fileNameList)



        return url;
    }

    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
        // 파일 이름이 증복되지 않게 하기 위해 랜덤으로 UUID 생성
    }

    private String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
            // 확장자 ex) asd.png 일 때 확장자 전까지 파일 이름을 반환
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ")입니다.");
        }
    }

    public void deleteImage(String fileName) {

        amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, fileName));

    }
}




