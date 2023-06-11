package com.blog.blog.controller;

import com.blog.blog.playload.ApiResponse;
import com.blog.blog.playload.PostDto;
import com.blog.blog.playload.PostResponse;
import com.blog.blog.service.FileService;
import com.blog.blog.service.PostService;
import com.blog.blog.utils.AppConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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


    static Logger logger= LogManager.getLogger(PostController.class);

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
        logger.info("entering..........create post of postcontroller");
        PostDto createPost = this.postService.createPost(postDto, userId, catId);
        logger.info("create post of postcontroller  executing..........");
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
        logger.info("enter....get all post of postcontroller ");

        PostResponse allPost = this.postService.getALLPost(pageNumber, pageSize,sortBy,sortDir);
        logger.info(" all post of postcontroller exit....");
        return new ResponseEntity<PostResponse>(allPost,HttpStatus.OK);
    }

    //get post details by id single post

    @GetMapping("/bypostId/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("postId") int postId)
    {
        logger.info("enter.....get post by id of postcontroller ");
        PostDto postById = this.postService.getPostById(postId);
        logger.info(" post by id of postcontroller exit.....");
        return new ResponseEntity<PostDto>(postById,HttpStatus.OK);
    }

    //update post
    @PutMapping("/up/post/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable("postId") int postId)
    {
        logger.info("..enter......update post by id of postcontroller");
        PostDto postDto1 = this.postService.updatePost(postDto, postId);
        logger.info(" post by id of postcontroller exit.....");
        return new ResponseEntity<PostDto>(postDto1,HttpStatus.OK);
    }

    //delete post
    @DeleteMapping("d/{postId}")
    public ApiResponse deletePost(@PathVariable("postId") int postId)
    {
        logger.info("enter.........delete post by id of postcontroller");
        this.postService.deletePost(postId);
        logger.info("delete post by id of postcontroller exit......... ");
        return new ApiResponse("Post delete Successfully",true);
    }



    //get by users post
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable("userId") int userId)
    {
        logger.info("enter......get post by user of postcontroller ");
        List<PostDto> posts = this.postService.getPostsByUser(userId);
        logger.info(".post by user of postcontroller exit.......");
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    //get by categories

    @GetMapping("/cat/{catId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategories(@PathVariable("catId") int catId)
    {
        logger.info("enter.......get post by categories of postcontroller ");
        List<PostDto> posts = this.postService.getPostByCategories(catId);
        logger.info("post by categories of postcontroller exit .........");
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    //seraching post

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keyword") String keyword)
    {
        logger.info("enter....search by post of postcontroller");
        List<PostDto> result = this.postService.serachPost(keyword);
        logger.info("search by post of postcontroller exit......");
        return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
    }


    //file upload controller

    //post image upload

    @PostMapping("/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image, @PathVariable("postId") int postId) throws IOException {

        logger.info("start........upload image of postcontroller");
        //update karegeDb mai
        PostDto postDto = this.postService.getPostById(postId);


        //set file name
        String fileName = this.fileService.uploadImage(path, image);


        postDto.setImageName(fileName);

        PostDto updatePost = this.postService.updatePost(postDto, postId);
        logger.info("exit........upload image of postcontroller");
        return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
    }

    //method of serve file
    @GetMapping(value = "/post/image/{imageName}")
    public void dowanloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws Exception
    {

        InputStream resourse = this.fileService.getResourse(path, imageName);
        logger.info("enter......search file of postcontroller");
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);

        StreamUtils.copy(resourse,response.getOutputStream());
        logger.info("Exit...... serach file of postcontroller");
    }
}
