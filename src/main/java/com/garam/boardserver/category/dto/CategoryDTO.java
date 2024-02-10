package com.garam.boardserver.category.dto;

import com.garam.boardserver.category.enums.SortStatus;
import lombok.*;


@Data
public class CategoryDTO {

    private Long id;
    private String name;
    private SortStatus sortStatus;
    private int searchCount;
    private int pagingStartOffset;

    public CategoryDTO(Long id, String name, SortStatus sortStatus, int searchCount, int pagingStartOffset) {
        this.id = id;
        this.name = name;
        this.sortStatus = sortStatus;
        this.searchCount = searchCount;
        this.pagingStartOffset = pagingStartOffset;
    }
}
