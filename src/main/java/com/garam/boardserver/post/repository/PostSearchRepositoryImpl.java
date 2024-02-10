package com.garam.boardserver.post.repository;

import com.garam.boardserver.category.enums.SortStatus;
import com.garam.boardserver.post.dto.PostDTO;
import com.garam.boardserver.post.dto.QPostDTO;
import com.garam.boardserver.post.dto.request.PostSearchRequest;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.garam.boardserver.post.entity.QPost.*;

public class PostSearchRepositoryImpl implements PostSearchRepositoryCustom{

	private final JPAQueryFactory queryFactory;

	public PostSearchRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public List<PostDTO> selectPosts(PostSearchRequest postSearchRequest) {

		return queryFactory.select(new QPostDTO(
			post.id
			, post.name
			, post.isAdmin
			, post.contents
			, post.createTime
			, post.views
			, post.categoryId
			, post.userId
			, post.fileId
			, post.updateTime
			))
			.from(post)
			.where(
				nameLike(postSearchRequest.getName())
				, contentsLike(postSearchRequest.getContents())
				, categoryIdEq(postSearchRequest.getCategoryId())
			).orderBy(createTimeDesc(postSearchRequest.getSortStatus()),
				createTimeAsc(postSearchRequest.getSortStatus()),
				categoryDesc(postSearchRequest.getSortStatus())
			)
			.fetch();
	}

	private static OrderSpecifier<Long> categoryDesc(SortStatus status) {
		if(!status.name().equals(SortStatus.CATEGORIES.name())) {
			return null;
		}

		return post.categoryId.desc();
	}

	private static OrderSpecifier<LocalDateTime> createTimeDesc(SortStatus status) {
		if(!status.name().equals(SortStatus.NEWEST.name())) {
			return null;
		}

		return post.createTime.desc();
	}

	private static OrderSpecifier<LocalDateTime> createTimeAsc(SortStatus status) {
		if(!status.name().equals(SortStatus.OLDEST.name())) {
			return null;
		}

		return post.createTime.asc();
	}

	@Override
	public List<PostDTO> getPostByTag(String tagName) {
		return new ArrayList<PostDTO>();
	}

	private static BooleanExpression categoryIdEq(Long categoryId) {
		return categoryId != null ? post.categoryId.eq(categoryId) : null;
	}

	private static BooleanExpression contentsLike(String contents) {
		return StringUtils.hasText(contents) ? post.name.like(contents) : null;
	}

	private static BooleanExpression nameLike(String name) {
		return StringUtils.hasText(name) ? post.name.like(name) : null;
	}
}
