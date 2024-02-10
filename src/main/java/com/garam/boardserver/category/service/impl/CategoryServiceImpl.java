package com.garam.boardserver.category.service.impl;


import com.garam.boardserver.category.entity.Category;
import com.garam.boardserver.category.dto.CategoryDTO;
import com.garam.boardserver.category.repository.CategoryRepository;
import com.garam.boardserver.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


@Log4j2
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void register(String accountId, CategoryDTO categoryDTO) {
        if (accountId == null){
            log.error("register ERROR! {}", categoryDTO);
            throw new RuntimeException("register ERROR! 상품 카테고리 등록 메서드를 확인해주세요\n" + "Params : " + categoryDTO);
        }

        Category category = new Category(categoryDTO);
        categoryRepository.save(category);

    }

    @Override
    public void update(CategoryDTO categoryDTO) {
        if (categoryDTO == null){
            log.error("update ERROR! {}", categoryDTO);
            throw new RuntimeException("update ERROR! 물품 카테고리 변경 메서드를 확인해주세요\n" + "Params : " + categoryDTO);
        }

        Category category = categoryRepository.findById(categoryDTO.getId()).orElse(null);

        if(category != null){
         category.setName(categoryDTO.getName());
//         category.setSearchCount(categoryDTO.getSearchCount());
        }
    }

    @Override
    public void delete(Long categoryId) {
        if(categoryId == null){
            log.error("deleteCategory ERROR! {}", categoryId);
            throw new RuntimeException("deleteCategory ERROR! 물품 카테고리 삭제 메서드를 확인해주세요\n" + "Params : " + categoryId);
        }

        categoryRepository.deleteById(categoryId);
    }
}
