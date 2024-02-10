package com.garam.boardserver.post.service.impl;

import com.garam.boardserver.member.entity.Member;
import com.garam.boardserver.member.repository.MemberRepository;
import com.garam.boardserver.post.dto.PostDTO;
import com.garam.boardserver.post.entity.Post;
import com.garam.boardserver.post.repository.PostRepository;
import com.garam.boardserver.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Log4j2
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @CacheEvict(value="getProducts", allEntries = true)
    @Override
    public void register(String id, PostDTO postDTO) {

        Member member = memberRepository.findByUserId(id);

        if (member == null){
            log.error("register ERROR! {}", postDTO);
            throw new RuntimeException("register ERROR! 상품 등록 메서드를 확인해주세요\n" + "Params : " + postDTO);
        }

        Post post = new Post(postDTO);
        post.setUserId(member.getId());
        post.setCreateTime(LocalDateTime.now());

        postRepository.save(post);
    }

    @Override
    public List<PostDTO> getMyProducts(Long userId) {

        List<Post> posts = postRepository.findAllByUserId(userId);
        List<PostDTO> result = new ArrayList<>();
        for(Post post:posts){
            result.add(new PostDTO(post));
        }
        return result;
    }

    @Override
    public void updateProducts(PostDTO postDTO) {
        if( postDTO == null && postDTO.getId() == 0 && postDTO.getUserId() == 0 ) {
            log.error("updateProducts ERROR! {}", postDTO);
            throw new RuntimeException("updateProducts ERROR! 물품 변경 메서드를 확인해주세요\n" + "Params : " + postDTO);
        }

        Post post = postRepository.findById(postDTO.getId()).orElse(null);

        if(post != null){
            post.setName(postDTO.getName());
            post.setViews(postDTO.getViews());
            post.setCategoryId(postDTO.getCategoryId());
            post.setUserId(postDTO.getUserId());
            post.setFileId(postDTO.getFileId());
            post.setUpdateTime(LocalDateTime.now());
        }
    }

    @Override
    public void deleteProduct(Long userId, Long productId) {
        if (userId == 0 && productId == 0) {
            log.error("deleteProudct ERROR! {}", productId);
            throw new RuntimeException("updateProducts ERROR! 물품 삭제 메서드를 확인해주세요\n" + "Params : " + productId);
        }

        postRepository.deleteById(productId);
    }
}
