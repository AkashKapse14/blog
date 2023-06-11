package com.blog.blog.controller;

import com.blog.blog.model.CommentModel;
import com.blog.blog.playload.ApiResponse;
import com.blog.blog.playload.CommentDto;
import com.blog.blog.playload.PostDto;
import com.blog.blog.service.CommentService;
import com.blog.blog.service.PostService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/com")
public class CommentController {

    static Logger logger= LogManager.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private PostService postService;

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable("postId") int postId)
            
    {
        logger.info("enter.....create comment of CommentController ");

        CommentDto comment = this.commentService.createComment(commentDto, postId);

        logger.info("create comment of CommentController exit........");
        return new ResponseEntity<CommentDto>(comment, HttpStatus.CREATED);
    }


    @DeleteMapping("/d/{commId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable("commId") int commId)
    {
        logger.info("enter.....delete comment of CommentController ");
        this.commentService.deleteComment(commId);

        logger.info("delete comment of CommentController exit.....");
        return new ResponseEntity<ApiResponse>(new ApiResponse("Delete comment",true ),HttpStatus.OK);
    }
}
