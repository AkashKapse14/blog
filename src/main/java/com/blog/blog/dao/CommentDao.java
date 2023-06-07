package com.blog.blog.dao;

import com.blog.blog.model.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<CommentModel,Integer> {
}
