package com.garam.boardserver.post.service;


import com.garam.boardserver.post.dto.PostDTO;

import java.util.List;

public interface PostService {

    void register(String id, PostDTO postDTO);

    List<PostDTO> getMyProducts(Long accountId);

    void updateProducts(PostDTO postDTO);

    void deleteProduct(Long userId, Long productId);
}
