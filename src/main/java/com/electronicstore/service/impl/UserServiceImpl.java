package com.electronicstore.service.impl;

import com.electronicstore.dtos.UserDto;
import com.electronicstore.entities.User;
import com.electronicstore.exception.ResourceNotFoundException;
import com.electronicstore.repository.UserRepository;
import com.electronicstore.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;



    @Override
    public UserDto createUser(UserDto userDto) {

        //generate unique Id in String formate
        String userId = UUID.randomUUID().toString();
        userDto.setUserId(userId);

        User user= dtoToEntity(userDto); // WE USE DTO CLASS TO TRANSFER OBJECT SO WE CONVERT IT INTO ENTITY CLASS FIRST

        User savedUser = userRepository.save(user);

       UserDto newDto= entityToDto(savedUser);

        return newDto;
    }


    @Override
    public UserDto updateUser(UserDto userDto, String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found exception"));

        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setImageName(userDto.getImageName());

        User updatedUser = userRepository.save(user);
        UserDto updatedDto = entityToDto(updatedUser);

        return updatedDto;
    }

    @Override
    public void deleteUser(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found exception"));
        userRepository.delete(user);

    }

    @Override
    public List<UserDto> getAllUser() {

        List<User> users = userRepository.findAll();
        List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());


        return dtoList;
    }

    @Override
    public UserDto getUserById(String userId) {
        User found = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("not found"));
        return entityToDto(found);
    }

    @Override
    public UserDto getUserByEmail(String email) {  //FOR FINDING BY EMAIL WE NEED TO CREATE METHOD IN REPOSITORY CLASS BECAUSE IT NOT PRESENT IN JPAREPO
        User found = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("not found"));

        return entityToDto(found);
    }

    @Override
    public List<UserDto> searchUser(String keyword) {

        List<User> list = userRepository.findByNameContaining(keyword);
        List<UserDto> dtoList = list.stream().map(user -> entityToDto(user)).collect(Collectors.toList());

        return dtoList;

    }


    private UserDto entityToDto(User savedUser) {

        // It is used when we dont use model Mapper

//        UserDto userDto = UserDto.builder()
//                .userId(savedUser.getUserId())
//                .name(savedUser.getName())
//                .about(savedUser.getAbout())
//                .email(savedUser.getEmail())
//                .password(savedUser.getPassword())
//                .gender(savedUser.getGender())
//                .imageName(savedUser.getImageName()).build();

            return mapper.map(savedUser,UserDto.class);

    }

    private User dtoToEntity(UserDto userDto) {

//        User user = User.builder()
//                .userId(userDto.getUserId())
//                .name(userDto.getName())
//                .password(userDto.getPassword())
//                .about(userDto.getAbout())
//                .email(userDto.getEmail())
//                .gender(userDto.getGender())
//                .imageName(userDto.getImageName()).build();

        return mapper.map(userDto,User.class);
    }


}
