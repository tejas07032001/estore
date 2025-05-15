package com.electronicstore.service;

import com.electronicstore.dtos.UserDto;

import java.util.List;

public interface UserService {

    //CREATE
    UserDto createUser(UserDto userDto);

    //UPDATE
    UserDto updateUser(UserDto userDto,String userId);

    //DELETE
    void deleteUser(String userId);

    //GET USER
    List<UserDto> getAllUser();

    //GET SINGLE USER

    UserDto getUserById(String userId);

    UserDto getUserByEmail(String email);

    //SEARCH
    List<UserDto> searchUser(String keyword);


}
