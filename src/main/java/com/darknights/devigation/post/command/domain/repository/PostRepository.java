package com.darknights.devigation.post.command.domain.repository;

import com.darknights.devigation.post.command.domain.aggregate.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
