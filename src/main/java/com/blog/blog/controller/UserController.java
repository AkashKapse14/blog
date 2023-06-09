package com.blog.blog.controller;

import com.blog.blog.model.User;
import com.blog.blog.playload.ApiResponse;
import com.blog.blog.playload.UserDto;
import com.blog.blog.service.UserService;

import java.util.List;
import java.util.Map;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    static Logger logger= LogManager.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    //create
    @PostMapping("/")
   public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
   {
       logger.info("enter....create user");
       UserDto userCreate = this.userService.createUser(userDto);
       logger.info("create user exit.....");
       return new  ResponseEntity<>(userCreate,HttpStatus.CREATED);
   }

   @PutMapping("/{userId}")
   public ResponseEntity<UserDto> updateUser(@Valid   @RequestBody UserDto userDto,@PathVariable("userId") int userId)
   {
       logger.info("enter update user.......");
       UserDto updateUser = this.userService.updateUser(userDto, userId);
       logger.info("exit....update user");
       return  ResponseEntity.ok(updateUser);
   }

   //delete User

    @DeleteMapping("/d/{userId}")
    public ResponseEntity<ApiResponse> deleteUSer(@PathVariable("userId") int userId)
    {
        logger.info("enter....delete user");
        this.userService.deleteUser(userId);
        logger.info("delete user exit....");
        return new ResponseEntity(new ApiResponse("user deleted successfully",true), HttpStatus.OK);

    }

    //get user all

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getALLUser()
    {
        logger.info("enter...get all users and .....exit");
        return ResponseEntity.ok(this.userService.getAllUser());
    }

    //get Single User

    @GetMapping("/s/{userId}")
    public ResponseEntity<UserDto> getSingle(@PathVariable("userId") int userId)
    {
        logger.info("enter....get single user ...and exit......");
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }
}
