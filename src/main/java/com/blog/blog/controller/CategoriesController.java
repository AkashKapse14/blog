package com.blog.blog.controller;

import com.blog.blog.playload.ApiResponse;
import com.blog.blog.playload.CategoriesDto;
import com.blog.blog.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cat")
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @PostMapping("/")
    public ResponseEntity<CategoriesDto> createUser(@Valid @RequestBody CategoriesDto categoriesDto)
    {


        CategoriesDto categories = this.categoriesService.createCategories(categoriesDto);
        return new ResponseEntity<CategoriesDto>(categories,HttpStatus.CREATED);

    }

    @PutMapping("/up/{catId}")
    public ResponseEntity<CategoriesDto> updateCat(@Valid  @RequestBody CategoriesDto categoriesDto, @PathVariable("catId") int catId)
    {
        CategoriesDto updateCat = this.categoriesService.updateCat(categoriesDto, catId);
        return new ResponseEntity<CategoriesDto>(updateCat,HttpStatus.OK);
    }

    @DeleteMapping("/d/{catId}")
    public ResponseEntity<ApiResponse> deleteCat(@PathVariable("catId") int catId)
    {
        this.categoriesService.deleteCat(catId);
        return  new ResponseEntity<ApiResponse>(new ApiResponse("Category delete Successfully",true),HttpStatus.OK);
    }


    @GetMapping("/s/{catId}")
    public ResponseEntity<CategoriesDto> getSingleCat(@PathVariable("catId") int catId)
    {
        CategoriesDto singleCat = this.categoriesService.getSingleCat(catId);
        return  new ResponseEntity<CategoriesDto>(singleCat,HttpStatus.ACCEPTED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<CategoriesDto>> getAllCat()
    {
        List<CategoriesDto> allCat = this.categoriesService.getAllCat();
        return  ResponseEntity.ok(allCat);
    }
}
