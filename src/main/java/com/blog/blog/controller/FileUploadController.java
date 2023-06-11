package com.blog.blog.controller;

import com.blog.blog.playload.PostDto;
import com.blog.blog.service.FileService;
import com.blog.blog.service.PostService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;


public class FileUploadController {


    Logger logger= LogManager.getLogger(FileUploadController.class);

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;
    
    //post image upload

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPost(
            @RequestParam("image") MultipartFile image,
            @PathVariable("postId") int postId
            ) throws IOException {

        //update karegeDb mai
        PostDto postDto = this.postService.getPostById(postId);


        //set file name
        String fileName = this.fileService.uploadImage(path, image);


        postDto.setImageName(fileName);
        PostDto updatePost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
    }

    //method of serve file
    @GetMapping(value = "post/image/{imageName}")
    public void dowanloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws Exception
    {

        InputStream resourse = this.fileService.getResourse(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resourse,response.getOutputStream());
    }
}
