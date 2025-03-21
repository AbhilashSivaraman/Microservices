package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.mapper.*;
import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/register")
    public UserDto registerUser(@RequestBody UserDto userDTO) {
        User user = userMapper.toEntity(userDTO);
        User savedUser = userService.saveUser(user);
        return userMapper.toDTO(savedUser);
    }

     @PostMapping("/login")
     public UserDto loginUser(@RequestBody UserDto userDTO) {
         User user = userService.findByUsername(userDTO.getUsername());
         if (user != null && user.getPassword().equals(userDTO.getPassword())) {
             return userMapper.toDTO(user);
         }
         return null;
     }
}