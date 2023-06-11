package com.blog.blog.playload;

import com.blog.blog.model.CommentModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {


    private String title;

    private String content;

    private String imageName;

    private Date addDate;

    private  CategoriesDto categoriesDto;

    private UserDto userDto;

    //all comment yaha se he fetch karege

    private Set<CommentDto> comment=new HashSet<>();
}
