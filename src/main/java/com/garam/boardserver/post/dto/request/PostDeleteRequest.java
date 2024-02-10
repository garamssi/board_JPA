package com.garam.boardserver.post.dto.request;

import lombok.Data;

@Data
public class PostDeleteRequest {
	private Long id;
	private int accountId;
}
