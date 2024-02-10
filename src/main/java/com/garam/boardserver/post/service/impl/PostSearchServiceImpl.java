package com.garam.boardserver.post.service.impl;

import com.garam.boardserver.post.dto.PostDTO;
import com.garam.boardserver.post.dto.request.PostSearchRequest;
import com.garam.boardserver.post.entity.Post;
import com.garam.boardserver.post.repository.PostSearchRepository;
import com.garam.boardserver.post.service.PostSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Log4j2
public class PostSearchServiceImpl implements PostSearchService {

    private final PostSearchRepository postSearchRepository;

    @Async // -> 비동기가 갑자기 왜 추가 된거지?
    @Cacheable(value = "getProducts", key = "'getProducts' + #postSearchRequest.getName() + #postSearchRequest.getCategoryId()")
    @Override
    public List<PostDTO> getPosts(PostSearchRequest postSearchRequest) {
        List<PostDTO> postDTOList = null;
        try {
            postDTOList = postSearchRepository.selectPosts(postSearchRequest);
        } catch (RuntimeException e) {
            log.error("selectPosts 실패", e.getMessage());
        }
        return postDTOList;
    }

    public List<PostDTO> getPostByTag(String tagName) {
        List<PostDTO> postDTOList = null;
        try {
            postDTOList = postSearchRepository.getPostByTag(tagName);
        } catch (RuntimeException e) {
            log.error("getPostByTag 실패", e.getMessage());
        }
        return postDTOList;
    }
}
