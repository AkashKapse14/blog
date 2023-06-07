package com.blog.blog.service;

import com.blog.blog.model.CommentModel;
import com.blog.blog.playload.CommentDto;

public interface CommentService {

    public CommentDto createComment(CommentDto commentDto,int postId);

    public void deleteComment(int commId);

}
