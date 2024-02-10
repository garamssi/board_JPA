package com.garam.boardserver.category.entity;

import com.garam.boardserver.category.dto.CategoryDTO;
import com.garam.boardserver.category.enums.SortStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;


@Getter
@Setter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Category {

	@Id @GeneratedValue
	private Long id;

	private String name;
	private SortStatus sortStatus;
	private int searchCount;
	private int pagingStartOffset;

	public Category(CategoryDTO categoryDTO){
		this.id = categoryDTO.getId();
		this.name = categoryDTO.getName();
		this.sortStatus = categoryDTO.getSortStatus();
		this.searchCount = categoryDTO.getSearchCount();
		this.pagingStartOffset = categoryDTO.getPagingStartOffset();
	}

}
