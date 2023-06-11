package com.blog.blog.service;

import com.blog.blog.controller.CategoriesController;
import com.blog.blog.playload.CategoriesDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoriesService {

    Logger logger= LogManager.getLogger(CommentService.class);

    public CategoriesDto createCategories (CategoriesDto categoriesDto);

    public List<CategoriesDto> getAllCat();
    public CategoriesDto getSingleCat(int catId);

    public CategoriesDto updateCat(CategoriesDto categoriesDto,int catId);

    public void deleteCat(int catId);
}
