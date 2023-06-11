package com.blog.blog.service;

import com.blog.blog.model.CommentModel;
import com.blog.blog.playload.CommentDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface CommentService {

    Logger logger= LogManager.getLogger(CommentService.class);

    public CommentDto createComment(CommentDto commentDto,int postId);

    public void deleteComment(int commId);

}
