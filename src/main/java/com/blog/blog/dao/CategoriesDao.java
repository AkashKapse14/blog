package com.blog.blog.dao;

import com.blog.blog.model.CategoriesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesDao extends JpaRepository<CategoriesModel,Integer> {
}
