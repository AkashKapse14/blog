package com.blog.blog.controller;

import com.blog.blog.playload.ApiResponse;
import com.blog.blog.playload.PostDto;
import com.blog.blog.playload.PostResponse;
import com.blog.blog.service.FileService;
import com.blog.blog.service.PostService;
import com.blog.blog.utils.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Value("${project.image}")
    private String path;

    @Autowired
    private FileService fileService;

    //post create by user and categories
    @PostMapping("/user/{userId}/cat/{catId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable("userId") int userId,@PathVariable("catId") int catId)
    {
        PostDto createPost = this.postService.createPost(postDto, userId, catId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
    }

    //get ALl post

    @GetMapping("/all/posts")
    public ResponseEntity<PostResponse> getALlPost(@RequestParam(value = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false) int pageNumber,
                                                   @RequestParam(value = "pageSize",defaultValue = AppConstant.PAGE_SIZE,required = false) int pageSize,
                                                   @RequestParam(value = "sortBy",defaultValue = AppConstant.SORT_By,required = false) String sortBy,
                                                   @RequestParam(value = "sortDir",defaultValue = AppConstant.SORT_DIR,required = false) String sortDir
                                                   )

    {

        PostResponse allPost = this.postService.getALLPost(pageNumber, pageSize,sortBy,sortDir);
        return new ResponseEntity<PostResponse>(allPost,HttpStatus.OK);
    }

    //get post details by id single post

    @GetMapping("/bypostId/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("postId") int postId)
    {
        PostDto postById = this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(postById,HttpStatus.OK);
    }

    //update post
    @PutMapping("/up/post/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable("postId") int postId)
    {
        PostDto postDto1 = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(postDto1,HttpStatus.OK);
    }

    //delete post
    @DeleteMapping("d/{postId}")
    public ApiResponse deletePost(@PathVariable("postId") int postId)
    {
        this.postService.deletePost(postId);

        return new ApiResponse("Post delete Successfully",true);
    }



    //get by users post
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable("userId") int userId)
    {
        List<PostDto> posts = this.postService.getPostsByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    //get by categories

    @GetMapping("/cat/{catId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategories(@PathVariable("catId") int catId)
    {
        List<PostDto> posts = this.postService.getPostByCategories(catId);

        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    //seraching post

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keyword") String keyword)
    {
        List<PostDto> result = this.postService.serachPost(keyword);
        return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
    }


    //file upload controller

    //post image upload

    @PostMapping("/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image, @PathVariable("postId") int postId) throws IOException {

        //update karegeDb mai
        PostDto postDto = this.postService.getPostById(postId);


        //set file name
        String fileName = this.fileService.uploadImage(path, image);


        postDto.setImageName(fileName);
        PostDto updatePost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
    }

    //method of serve file
    @GetMapping(value = "/post/image/{imageName}")
    public void dowanloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws Exception
    {
        InputStream resourse = this.fileService.getResourse(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resourse,response.getOutputStream());
    }
}
