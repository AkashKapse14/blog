package com.blog.blog.controller;

import com.blog.blog.playload.ApiResponse;
import com.blog.blog.playload.CategoriesDto;
import com.blog.blog.service.CategoriesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/cat")
public class CategoriesController {

    Logger logger= LogManager.getLogger(CategoriesController.class);

    @Autowired
    private CategoriesService categoriesService;

    @PostMapping("/")
    public ResponseEntity<CategoriesDto> createUser(@Valid @RequestBody CategoriesDto categoriesDto)
    {

            logger.info("enter....create user of catcontroller ");
            CategoriesDto categories = this.categoriesService.createCategories(categoriesDto);

            logger.info("create user of catcontroller exit.......");
            return new ResponseEntity<CategoriesDto>(categories,HttpStatus.CREATED);



    }

    @PutMapping("/up/{catId}")
    public ResponseEntity<CategoriesDto> updateCat(@Valid  @RequestBody CategoriesDto categoriesDto, @PathVariable("catId") int catId)
    {
        logger.info("enter........Update categories of catcontroller ");
        CategoriesDto updateCat = this.categoriesService.updateCat(categoriesDto, catId);
        logger.info("update categories of catcontroller exit........");
        return new ResponseEntity<CategoriesDto>(updateCat,HttpStatus.OK);
    }

    @DeleteMapping("/d/{catId}")
    public ResponseEntity<ApiResponse> deleteCat(@PathVariable("catId") int catId)
    {
        logger.info("enter........delete categories of catcontroller ");
        this.categoriesService.deleteCat(catId);
        logger.info("delete categories exit.........");
        return  new ResponseEntity<ApiResponse>(new ApiResponse("Category delete Successfully",true),HttpStatus.OK);
    }


    @GetMapping("/s/{catId}")
    public ResponseEntity<CategoriesDto> getSingleCat(@PathVariable("catId") int catId)
    {
        logger.info("enter.......get single categories of catcontroller ");
        CategoriesDto singleCat = this.categoriesService.getSingleCat(catId);
        logger.info("get single categories of catcontroller exit................");
        return  new ResponseEntity<CategoriesDto>(singleCat,HttpStatus.ACCEPTED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<CategoriesDto>> getAllCat()
    {
        logger.info("get all categories of of catcontroller enter.........");
        List<CategoriesDto> allCat = this.categoriesService.getAllCat();
        logger.info("get all categories of of catcontroller exit..........");
        return  ResponseEntity.ok(allCat);
    }


}
