package com.garam.boardserver.post.dto.request;


import com.garam.boardserver.post.dto.PostDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostRequest {
	private String name;
	private String contents;
	private int views;
	private Long categoryId;
	private Long userId;
	private Long fileId;
	private LocalDateTime updateTime;


	public PostRequest(PostDTO postDTO){
		this.name = postDTO.getName();
		this.contents = postDTO.getContents();
		this.views = postDTO.getViews();
		this.categoryId = postDTO.getCategoryId();
		this.userId = postDTO.getUserId();
		this.fileId = postDTO.getFileId();
		this.updateTime = postDTO.getUpdateTime();
	}
}
