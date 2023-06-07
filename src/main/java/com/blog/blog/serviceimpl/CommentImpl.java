package com.blog.blog.serviceimpl;

import com.blog.blog.dao.CommentDao;
import com.blog.blog.dao.PostDao;
import com.blog.blog.exception.ResourceNotFoundException;
import com.blog.blog.model.CommentModel;
import com.blog.blog.model.PostModel;
import com.blog.blog.playload.CommentDto;
import com.blog.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentImpl implements CommentService {


    @Autowired
    private CommentDao commentDao;

    @Autowired
    private PostDao postDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, int postId) {

        PostModel postModel = this.postDao.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));

        CommentModel comment = this.modelMapper.map(postModel, CommentModel.class);
        comment.setPostModel(postModel);

        CommentModel savecomment = this.commentDao.save(comment);

        return this.modelMapper.map(savecomment,CommentDto.class);
    }

    @Override
    public void deleteComment(int commId) {

        CommentModel commentModel = this.commentDao.findById(commId).orElseThrow(() -> new ResourceNotFoundException("comment", "commId", commId));

        this.commentDao.delete(commentModel);

        //yaha se expection msg ni dega
        //  this.commentDao.deleteById(commId);
    }
}
