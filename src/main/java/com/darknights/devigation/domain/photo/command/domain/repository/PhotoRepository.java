package com.darknights.devigation.domain.photo.command.domain.repository;

import com.darknights.devigation.domain.photo.command.domain.aggregate.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
