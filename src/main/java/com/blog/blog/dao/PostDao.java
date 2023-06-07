package com.blog.blog.dao;

import com.blog.blog.model.CategoriesModel;
import com.blog.blog.model.PostModel;
import com.blog.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostDao extends JpaRepository<PostModel,Integer> {

    List<PostModel> findByUser(User user);

    List<PostModel> findByCategoriesModel (CategoriesModel categoriesModel);

    //searching method

    @Query("select p from PostModel p where p.title like:key")
    List<PostModel> findByTitleContaining(@Param("key") String title);

}
