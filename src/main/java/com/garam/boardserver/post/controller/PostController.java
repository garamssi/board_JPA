package com.garam.boardserver.post.controller;

import com.garam.boardserver.aop.LoginCheck;
import com.garam.boardserver.common.dto.response.CommonResponse;
import com.garam.boardserver.member.dto.MemberDTO;
import com.garam.boardserver.member.service.MemberService;
import com.garam.boardserver.post.dto.PostDTO;
import com.garam.boardserver.post.dto.request.PostDeleteRequest;
import com.garam.boardserver.post.dto.request.PostRequest;
import com.garam.boardserver.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
@Log4j2
public class PostController {

    private final PostService postService;
    private final MemberService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @LoginCheck(type = LoginCheck.UserType.USER)
    public ResponseEntity<CommonResponse<PostDTO>> registerPost(String accountId, @RequestBody PostDTO postDTO) {
        postService.register(accountId, postDTO);
        CommonResponse<PostDTO> commonResponse = new CommonResponse<>(HttpStatus.OK, "SUCCESS", "registerPost", postDTO);
        return ResponseEntity.ok(commonResponse);
    }

    @GetMapping("my-posts")
    @LoginCheck(type = LoginCheck.UserType.USER)
    public ResponseEntity<CommonResponse<List<PostDTO>>> myPostInfo(String accountId) {
        MemberDTO memberInfo = userService.getUserInfo(accountId);
        List<PostDTO> postDTOList = postService.getMyProducts(memberInfo.getId());
        CommonResponse<List<PostDTO>> commonResponse = new CommonResponse<>(HttpStatus.OK, "SUCCESS", "myPostInfo", postDTOList);
        return ResponseEntity.ok(commonResponse);
    }

    @PatchMapping("{postId}")
    @LoginCheck(type = LoginCheck.UserType.USER)
    public ResponseEntity<CommonResponse<PostRequest>> updatePosts(String accountId,
                                                                   @PathVariable(name = "postId") Long postId,
                                                                   @RequestBody PostRequest postRequest) {
        MemberDTO memberInfo = userService.getUserInfo(accountId);
        PostDTO postDTO = new PostDTO();
        postDTO.setId(postId);
        postDTO.setName(postRequest.getName());
        postDTO.setContents(postRequest.getContents());
        postDTO.setViews(postRequest.getViews());
        postDTO.setCategoryId(postRequest.getCategoryId());
        postDTO.setUserId(memberInfo.getId());
        postDTO.setFileId(postRequest.getFileId());
        postDTO.setUpdateTime(LocalDateTime.now());

        postService.updateProducts(postDTO);
        CommonResponse<PostRequest> commonResponse = new CommonResponse<>(HttpStatus.OK, "SUCCESS", "updatePosts", new PostRequest(postDTO));
        return ResponseEntity.ok(commonResponse);
    }

    @DeleteMapping("{postId}")
    @LoginCheck(type = LoginCheck.UserType.USER)
    public ResponseEntity<CommonResponse<PostDeleteRequest>> deleteposts(String accountId,
                                                                         @PathVariable(name = "postId") Long postId,
                                                                         @RequestBody PostDeleteRequest postDeleteRequest) {
        MemberDTO memberInfo = userService.getUserInfo(accountId);
        postService.deleteProduct(memberInfo.getId(), postId);
        CommonResponse<PostDeleteRequest> commonResponse = new CommonResponse<>(HttpStatus.OK, "SUCCESS", "deleteposts", postDeleteRequest);
        return ResponseEntity.ok(commonResponse);
    }


}
