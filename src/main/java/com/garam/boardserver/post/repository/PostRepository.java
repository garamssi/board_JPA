package com.garam.boardserver.post.repository;

import com.garam.boardserver.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findAllByUserId(Long userId);
}
