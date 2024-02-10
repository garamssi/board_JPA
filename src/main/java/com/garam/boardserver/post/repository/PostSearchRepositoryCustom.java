package com.garam.boardserver.post.repository;

import com.garam.boardserver.post.dto.PostDTO;
import com.garam.boardserver.post.dto.request.PostSearchRequest;

import java.util.List;

public interface PostSearchRepositoryCustom {

	public List<PostDTO> selectPosts(PostSearchRequest postSearchRequest);
	public List<PostDTO> getPostByTag(String tagName);

}
