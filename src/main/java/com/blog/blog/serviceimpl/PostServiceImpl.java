package com.blog.blog.serviceimpl;

import com.blog.blog.dao.CategoriesDao;
import com.blog.blog.dao.PostDao;
import com.blog.blog.dao.UserDao;
import com.blog.blog.exception.ResourceNotFoundException;
import com.blog.blog.model.CategoriesModel;
import com.blog.blog.model.PostModel;
import com.blog.blog.model.User;
import com.blog.blog.playload.PostDto;
import com.blog.blog.playload.PostResponse;
import com.blog.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CategoriesDao categoriesDao;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto, int userId, int catId) {

        User user = this.userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
        CategoriesModel categoriesModel = this.categoriesDao.findById(catId).orElseThrow(() -> new ResourceNotFoundException("categories", "catId", catId));


        PostModel post = this.modelMapper.map(postDto, PostModel.class);
        post.setImageName("default.png");
        post.setAddDate(new Date());
        post.setUser(user);
        post.setCategoriesModel(categoriesModel);

        PostModel newPost = this.postDao.save(post);
        return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, int postId) {

        PostModel postModel = this.postDao.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));

        postModel.setTitle(postDto.getTitle());
        postModel.setContent(postDto.getContent());
        postModel.setImageName(postDto.getImageName());

        PostModel updatePost = this.postDao.save(postModel);

        return this.modelMapper.map(updatePost,PostDto.class);
    }

    @Override
    public void deletePost(int postId) {

        PostModel postModel = this.postDao.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
        this.postDao.delete(postModel);
    }

    @Override
    public PostResponse getALLPost(int pageNumber, int pageSize,String sortBy,String sortDir) {

        //sorting
//        Sort sort=null;
//
//        if(sortDir.equalsIgnoreCase("asc"))
//        {
//            sort=Sort.by(sortBy).descending();
//        }
//        else
//        {
//          sort=Sort.by(sortBy).ascending();
//        }

        Sort sort=sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();





       //  pagnation
        PageRequest p = PageRequest.of(pageNumber,pageSize);
        Page<PostModel> allpost = this.postDao.findAll(p);


        List<PostModel> pageP = allpost.getContent();


        List<PostDto> postDtos = allpost.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        //make obj of post response send to clint reponse

        PostResponse postResponse=new PostResponse();

        postResponse.setContent(postDtos);
        postResponse.setPageNumber(allpost.getNumber());
        postResponse.setPageSize(allpost.getSize());
        postResponse.setTotalElement(allpost.getTotalElements());
        postResponse.setTotalPage(allpost.getTotalPages());
        postResponse.setLastPage(allpost.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(int postId) {

        PostModel postModel = this.postDao.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
        return this.modelMapper.map(postModel,PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategories(int catId) {

        CategoriesModel cat = this.categoriesDao.findById(catId).orElseThrow(() -> new ResourceNotFoundException("categories", "catId", catId));

        //this custom finder method use for find by categories
        List<PostModel> posts = this.postDao.findByCategoriesModel(cat);
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(int userId) {

        User user = this.userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        //custom finder method use for find by user
        List<PostModel> posts = this.postDao.findByUser(user);

        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> serachPost(String keyword) {

        //find by title this is custom finder method
        List<PostModel> posts = this.postDao.findByTitleContaining("%"+keyword+"%");
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }


}
