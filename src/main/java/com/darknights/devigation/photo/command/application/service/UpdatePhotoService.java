package com.darknights.devigation.photo.command.application.service;

import com.darknights.devigation.photo.command.application.dto.PhotoDTO;
import com.darknights.devigation.photo.command.domain.aggregate.entity.Photo;
import com.darknights.devigation.photo.command.domain.aggregate.entity.enumtype.PhotoCategory;
import com.darknights.devigation.photo.command.domain.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class UpdatePhotoService {

    private final PhotoRepository photoRepository;
    private final InsertPhotoService insertPhotoService;

    @Autowired
    public UpdatePhotoService(PhotoRepository photoRepository, InsertPhotoService insertPhotoService) {
        this.photoRepository = photoRepository;
        this.insertPhotoService = insertPhotoService;
    }

    public PhotoDTO updatePhoto(Long id, Long originId, MultipartFile photo, PhotoCategory category) throws IOException {
        photoRepository.deleteById(id);
        return insertPhotoService.insertPhoto(originId, photo, category);
    }
}
