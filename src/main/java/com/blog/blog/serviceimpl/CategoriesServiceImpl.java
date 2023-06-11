package com.blog.blog.serviceimpl;

import com.blog.blog.controller.LogController;
import com.blog.blog.dao.CategoriesDao;
import com.blog.blog.exception.ResourceNotFoundException;
import com.blog.blog.model.CategoriesModel;
import com.blog.blog.playload.CategoriesDto;
import com.blog.blog.service.CategoriesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriesServiceImpl implements CategoriesService {



    static Logger logger= LogManager.getLogger(CategoriesServiceImpl.class);

    @Autowired
    private CategoriesDao categoriesDao;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoriesDto createCategories(CategoriesDto categoriesDto) {

        logger.info("enter ....impl create categories");
        CategoriesModel cat = this.modelMapper.map(categoriesDto, CategoriesModel.class);
        CategoriesModel catsave = this.categoriesDao.save(cat);

        logger.info("impl create categories exit......");
        return this.modelMapper.map(catsave,CategoriesDto.class);

    }

    @Override
    public List<CategoriesDto> getAllCat() {

        List<CategoriesModel> all = this.categoriesDao.findAll();
        logger.info("get all categories impl enter.....");
        List<CategoriesDto> catDtos = all.stream().map((cat) -> this.modelMapper.map(cat, CategoriesDto.class)).collect(Collectors.toList());
        logger.info("get all categoriesimpl  exit.....");
        return  catDtos;
    }

    @Override
    public CategoriesDto getSingleCat(int catId) {
        logger.info("enter....get single of categoriesimpl");
        CategoriesModel catSingle = this.categoriesDao.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Categories", "catId", catId));
        logger.info("get single of categoriesimpl exit.......");
        return this.modelMapper.map(catSingle,CategoriesDto.class);
    }

    @Override
    public CategoriesDto updateCat(CategoriesDto categoriesDto, int catId) {

        logger.info("enter...... update categories findby id of categoriesimpl");
        CategoriesModel cat = this.categoriesDao.findById(catId).orElseThrow(() ->new ResourceNotFoundException("Categories ", "catId ", catId));
        cat.setCatTitle(categoriesDto.getCatTitle());
        cat.setCatDescription(categoriesDto.getCatDescription());

        CategoriesModel updateCat = this.categoriesDao.save(cat);

        logger.info("update cat of categoriesimpl id exit........  ");
        return this.modelMapper.map(updateCat,CategoriesDto.class);
    }

    @Override
    public void deleteCat(int catId) {

        logger.info("enter.......delete of categoriesimpl by id ");
        CategoriesModel cat = this.categoriesDao.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Categories", "catId", catId));
        logger.info("delete of categoriesimpl by id exit........");
        this.categoriesDao.delete(cat);
    }
}
