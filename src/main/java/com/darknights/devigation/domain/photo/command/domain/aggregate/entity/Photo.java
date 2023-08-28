package com.darknights.devigation.domain.photo.command.domain.aggregate.entity;

import com.darknights.devigation.domain.photo.command.domain.aggregate.entity.enumtype.PhotoCategory;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "PHOTO_TB")
@Getter
public class Photo {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long originId;    // 특정 게시물의 ID

    @Column(name = "PHOTO_CATEGORY", nullable = false)
    @Enumerated(EnumType.STRING)
    private PhotoCategory photoCategory;

    @Column(nullable = false)
    private String photoName;

    @Column(nullable = false)
    private String photoRename;

    @Column(nullable = false)
    private String photoRoot;

    public Photo() {
    }

    public Photo(Long originId, PhotoCategory photoCategory, String photoName, String photoRename, String photoRoot) {
        this.originId = originId;
        this.photoCategory = photoCategory;
        this.photoName = photoName;
        this.photoRename = photoRename;
        this.photoRoot = photoRoot;
    }
}
