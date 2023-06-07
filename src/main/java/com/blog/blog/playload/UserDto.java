package com.blog.blog.playload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private int userId;

    @NotEmpty
    @Size(min = 5,message = "username must be 5 characters")
    private String name;
    @Email(message = "email address not valid")
    private String email;
    @NotEmpty
    @Size(min = 3,max = 10,message = "password must be 3 char & Max of 10 Char")
    private String password;
    @NotEmpty
    private String about;
}
