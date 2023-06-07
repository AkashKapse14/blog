package com.blog.blog.service;

import com.blog.blog.playload.CategoriesDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoriesService {

    public CategoriesDto createCategories (CategoriesDto categoriesDto);

    public List<CategoriesDto> getAllCat();
    public CategoriesDto getSingleCat(int catId);

    public CategoriesDto updateCat(CategoriesDto categoriesDto,int catId);

    public void deleteCat(int catId);
}
