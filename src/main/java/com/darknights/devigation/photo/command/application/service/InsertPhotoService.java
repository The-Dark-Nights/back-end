package com.darknights.devigation.photo.command.application.service;

import com.darknights.devigation.photo.command.application.dto.PhotoDTO;
import com.darknights.devigation.photo.command.domain.aggregate.entity.Photo;
import com.darknights.devigation.photo.command.domain.repository.PhotoRepository;
import com.darknights.devigation.photo.command.domain.aggregate.entity.enumtype.PhotoCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class InsertPhotoService {

    private final PhotoRepository photoRepository;

    @Autowired
    public InsertPhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Value("${custom.path.upload-images}")
    private String uploadPath;

    @Transactional
    public PhotoDTO insertPhoto(Long originId, MultipartFile photo,
                                PhotoCategory category) throws IOException {

        if (photo.isEmpty()) {
            return null;
        }

        String savedFolder = null;

        if (category == PhotoCategory.POST) {
            savedFolder = "post/";
        }

        File savedFile = null;

        File photoFolder = new File(uploadPath + savedFolder);
        System.out.println("photoFolder = " + photoFolder);
        if (!photoFolder.exists()) {
            photoFolder.mkdirs();
        }

        String originPhotoName = photo.getOriginalFilename();

        if (originPhotoName != null) {
            String ext = originPhotoName.substring(originPhotoName.lastIndexOf("."));

            String savedName = UUID.randomUUID().toString().replace("-", "") + ext;

            String savedPath = "/static/upload-images/" + savedFolder + savedName;

            savedFile =new File(photoFolder,savedName);
            photo.transferTo(savedFile);

            PhotoDTO photoDTO = new PhotoDTO(originId,originPhotoName,savedName,category,savedPath);

            Photo photoEntity =new Photo(
                    photoDTO.getOriginId(),
                    photoDTO.getPhotoCategory(),
                    photoDTO.getPhotoName(),
                    photoDTO.getPhotoRename(),
                    photoDTO.getPhotoRoot()
            );

            photoRepository.save(photoEntity);

            // DTO타입 반환
            return new PhotoDTO(
                    photoEntity.getId(),
                    photoEntity.getOriginId(),
                    photoEntity.getPhotoName(),
                    photoEntity.getPhotoRename(),
                    photoEntity.getPhotoCategory(),
                    photoEntity.getPhotoRoot());
        }

       return null;
    }

}
