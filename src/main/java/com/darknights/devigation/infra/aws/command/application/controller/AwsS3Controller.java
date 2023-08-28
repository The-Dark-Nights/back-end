package com.darknights.devigation.infra.aws.command.application.controller;

import com.darknights.devigation.infra.aws.command.application.service.AwsS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class AwsS3Controller {

    private final AwsS3Service awsS3Service;

    @Autowired
    public AwsS3Controller(AwsS3Service awsS3Service){
        this.awsS3Service=awsS3Service;
    }

    // test용 controller
    @PostMapping("/image")
    public String uploadImage(@RequestPart(value="file",required = false) MultipartFile multipartFile){
        return awsS3Service.uploadImage(multipartFile);
    }

}
