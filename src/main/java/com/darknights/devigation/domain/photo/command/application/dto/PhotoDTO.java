package com.darknights.devigation.domain.photo.command.application.dto;

import com.darknights.devigation.domain.photo.command.domain.aggregate.entity.enumtype.PhotoCategory;
import lombok.Getter;

@Getter
public class PhotoDTO {

    private Long id;
    private Long originId;
    private String photoName;
    private String photoRename;
    private PhotoCategory photoCategory;
    private String photoRoot;

    public PhotoDTO(Long originId, String photoName, String photoRename, PhotoCategory photoCategory, String photoRoot) {
        this.originId = originId;
        this.photoName = photoName;
        this.photoRename = photoRename;
        this.photoCategory = photoCategory;
        this.photoRoot = photoRoot;
    }

    public PhotoDTO(Long id, Long originId, String photoName, String photoRename, PhotoCategory photoCategory, String photoRoot) {
        this.id = id;
        this.originId = originId;
        this.photoName = photoName;
        this.photoRename = photoRename;
        this.photoCategory = photoCategory;
        this.photoRoot = photoRoot;
    }

}
