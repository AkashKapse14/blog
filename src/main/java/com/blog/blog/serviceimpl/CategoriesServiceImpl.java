package com.blog.blog.serviceimpl;

import com.blog.blog.dao.CategoriesDao;
import com.blog.blog.exception.ResourceNotFoundException;
import com.blog.blog.model.CategoriesModel;
import com.blog.blog.playload.CategoriesDto;
import com.blog.blog.service.CategoriesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriesServiceImpl implements CategoriesService {


    @Autowired
    private CategoriesDao categoriesDao;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoriesDto createCategories(CategoriesDto categoriesDto) {

        CategoriesModel cat = this.modelMapper.map(categoriesDto, CategoriesModel.class);
        CategoriesModel catsave = this.categoriesDao.save(cat);
        return this.modelMapper.map(catsave,CategoriesDto.class);

    }

    @Override
    public List<CategoriesDto> getAllCat() {

        List<CategoriesModel> all = this.categoriesDao.findAll();
        List<CategoriesDto> catDtos = all.stream().map((cat) -> this.modelMapper.map(cat, CategoriesDto.class)).collect(Collectors.toList());
        return  catDtos;
    }

    @Override
    public CategoriesDto getSingleCat(int catId) {

        CategoriesModel catSingle = this.categoriesDao.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Categories", "catId", catId));
        return this.modelMapper.map(catSingle,CategoriesDto.class);
    }

    @Override
    public CategoriesDto updateCat(CategoriesDto categoriesDto, int catId) {

        CategoriesModel cat = this.categoriesDao.findById(catId).orElseThrow(() ->new ResourceNotFoundException("Categories ", "catId ", catId));
        cat.setCatTitle(categoriesDto.getCatTitle());
        cat.setCatDescription(categoriesDto.getCatDescription());

        CategoriesModel updateCat = this.categoriesDao.save(cat);
        return this.modelMapper.map(updateCat,CategoriesDto.class);
    }

    @Override
    public void deleteCat(int catId) {

        CategoriesModel cat = this.categoriesDao.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Categories", "catId", catId));
        this.categoriesDao.delete(cat);
    }
}
