package com.garam.boardserver.post.service;


import com.garam.boardserver.post.dto.PostDTO;
import com.garam.boardserver.post.dto.request.PostSearchRequest;

import java.util.List;

public interface PostSearchService {
    List<PostDTO> getPosts(PostSearchRequest postSearchRequest);
    List<PostDTO> getPostByTag(String tagName);
}
