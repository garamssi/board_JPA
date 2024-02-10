package com.garam.boardserver.post.dto.response;

import com.garam.boardserver.post.dto.PostDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PostSearchResponse {
	private List<PostDTO> postDTOList;
}
