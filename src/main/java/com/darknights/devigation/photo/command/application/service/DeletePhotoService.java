package com.darknights.devigation.photo.command.application.service;

import com.darknights.devigation.photo.command.domain.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// 게시물이 삭제되면서 사진도 삭제해야한다.
@Service
public class DeletePhotoService {

    private final PhotoRepository photoRepository;

    @Autowired
    public DeletePhotoService(PhotoRepository photoRepository){
        this.photoRepository=photoRepository;
    }

    public void deletePhoto(Long id){
        photoRepository.deleteById(id);
    }
}
