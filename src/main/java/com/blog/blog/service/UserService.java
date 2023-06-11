package com.blog.blog.service;

import com.blog.blog.playload.UserDto;

import java.util.List;

public interface UserService {

 UserDto createUser(UserDto user);

 UserDto updateUser(UserDto user,Integer userId);

 UserDto getUserById(Integer userId);
 List<UserDto> getAllUser();

 void deleteUser(Integer userId);
}
