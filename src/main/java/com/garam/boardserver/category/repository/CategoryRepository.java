package com.garam.boardserver.category.repository;


import com.garam.boardserver.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
