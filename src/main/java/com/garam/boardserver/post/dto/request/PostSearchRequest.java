package com.garam.boardserver.post.dto.request;

import com.garam.boardserver.category.enums.SortStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Data
public class PostSearchRequest {
    private Long id;
    private String name;
    private String contents;
    private int views;
    private Long categoryId;
    private Long userId;
    private SortStatus sortStatus;
    private String tagName;

    @QueryProjection
    public PostSearchRequest(Long id, String name, String contents, int views, Long categoryId, Long userId, SortStatus sortStatus ){
        this.id = id;
        this.name = name;
        this.contents = contents;
        this.views = views;
        this.categoryId = categoryId;
        this.userId = userId;
        this.sortStatus = sortStatus;
    }
}
