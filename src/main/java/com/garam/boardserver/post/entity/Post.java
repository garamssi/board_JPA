package com.garam.boardserver.post.entity;

import com.garam.boardserver.post.dto.PostDTO;
import com.garam.boardserver.post.dto.request.PostRequest;
import com.garam.boardserver.post.dto.request.PostSearchRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post {

	@Id @GeneratedValue
	private Long id;
	private String name;
	private Long isAdmin;
	private String contents;
	private LocalDateTime createTime;
	private int views;
	private Long categoryId;
	private Long userId;
	private Long fileId;
	private LocalDateTime updateTime;

	public Post(PostDTO postDTO) {
		this.id = postDTO.getId();
		this.name = postDTO.getName();
		this.isAdmin = postDTO.getIsAdmin();
		this.contents = postDTO.getContents();
		this.createTime = postDTO.getCreateTime();
		this.views = postDTO.getViews();
		this.categoryId = postDTO.getCategoryId();
		this.userId = postDTO.getUserId();
		this.fileId = postDTO.getFileId();
		this.updateTime = postDTO.getUpdateTime();
	}


}
