package com.garam.boardserver.post.repository;

import com.garam.boardserver.post.dto.PostDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostSearchRepository extends JpaRepository<PostDTO, Long>, PostSearchRepositoryCustom {

}
