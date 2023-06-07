package com.blog.blog.serviceimpl;

import com.blog.blog.dao.UserDao;
import com.blog.blog.exception.ResourceNotFoundException;
import com.blog.blog.model.User;
import com.blog.blog.playload.UserDto;
import com.blog.blog.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {
        User user=this.dtoToUser(userDto);
        User savedUser=this.userDao.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        User user=this.userDao.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User userUpdate=this.userDao.save(user);
        UserDto userDto1=this.userToDto(userUpdate);
        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {

        User user=this.userDao.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {

        List<User> users = this.userDao.findAll();

        List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {

        //first find
        User user = this.userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        this.userDao.delete(user);
    }

    public User dtoToUser(UserDto userDto)
    {

        User user=this.modelMapper.map(userDto,User.class);
//        User user=new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
//        return user;

        return user;
    }

    public UserDto userToDto(User user)
    {

        UserDto userDto=this.modelMapper.map(user,UserDto.class);
//        UserDto userDto=new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
//        return  userDto;

        return userDto;
    }
}
