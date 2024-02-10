package com.garam.boardserver.post.dto;

import com.garam.boardserver.post.entity.Post;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostDTO {
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

	public PostDTO(Post post) {
		this.id = post.getId();
		this.name = post.getName();
		this.isAdmin = post.getIsAdmin();
		this.contents = post.getContents();
		this.createTime = post.getCreateTime();
		this.views = post.getViews();
		this.categoryId = post.getCategoryId();
		this.userId = post.getUserId();
		this.fileId = post.getFileId();
		this.updateTime = post.getUpdateTime();
	}

    @QueryProjection
	public PostDTO(Long id, String name, Long isAdmin, String contents, LocalDateTime createTime, int views, Long categoryId, Long userId, Long fileId, LocalDateTime updateTime) {
        this.id = id;
        this.name =  name;
        this.isAdmin =  isAdmin;
        this.contents = contents;
        this.createTime =  createTime;
        this.views =  views;
        this.categoryId =  categoryId;
        this.userId =  userId;
        this.fileId = fileId;
        this.updateTime = updateTime;
	}
}
