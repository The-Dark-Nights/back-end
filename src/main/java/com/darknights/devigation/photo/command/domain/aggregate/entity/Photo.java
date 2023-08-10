package com.darknights.devigation.photo.command.domain.aggregate.entity;

import com.darknights.devigation.photo.command.domain.aggregate.entity.enumtype.PhotoCategory;

import javax.persistence.*;

@Entity
@Table(name="PHOTO_TB")
public class Photo {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Long originId;

    @Column(name = "PHOTO_CATEGORY", nullable = false)
    @Enumerated(EnumType.STRING)
    private PhotoCategory photoCategory;

    @Column(nullable = false)
    private String photoName;
    @Column(nullable = false)
    private String photoRename;
    @Column(nullable = false)
    private String photoRoot;
}
