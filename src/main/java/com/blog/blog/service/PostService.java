package com.blog.blog.service;

import com.blog.blog.model.PostModel;
import com.blog.blog.playload.PostDto;
import com.blog.blog.playload.PostResponse;

import java.util.List;

public interface PostService {
    //create
    public PostDto createPost(PostDto postDto, int userId, int catId);

    //update

    public PostDto updatePost(PostDto postDto,int postId);

    //delete

    public void deletePost(int postId);

    //getAllPost

    public PostResponse getALLPost(int pageNumber, int pageSize,String sortBy,String sortDir);

    //getSingle

    public PostDto getPostById(int postId);

    //get All post by categories

    List<PostDto> getPostByCategories(int catId);

    //get All post by User

    List<PostDto> getPostsByUser(int userId);

    //serach poats

    List<PostDto> serachPost(String keyword);
}
