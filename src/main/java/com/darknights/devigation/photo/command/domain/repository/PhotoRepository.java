package com.darknights.devigation.photo.command.domain.repository;

import com.darknights.devigation.photo.command.domain.aggregate.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
