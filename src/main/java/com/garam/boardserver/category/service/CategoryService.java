package com.garam.boardserver.category.service;


import com.garam.boardserver.category.dto.CategoryDTO;

public interface CategoryService {

    void register(String accountId, CategoryDTO categoryDTO);

    void update(CategoryDTO categoryDTO);

    void delete(Long categoryId);
}
