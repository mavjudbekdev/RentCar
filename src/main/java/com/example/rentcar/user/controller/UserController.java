package com.example.rentcar.user.controller;

import com.example.rentcar.user.dto.UserResponseDto;
import com.example.rentcar.user.dto.UserUpdateDto;
import com.example.rentcar.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponseDto create(@RequestBody UserResponseDto responseDto){
       return userService.create(responseDto);
    }

    @GetMapping
    public List<UserResponseDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable("id") UUID Id) {
        return userService.getUser(Id);
    }

    @PutMapping("/{id}")
    public UserResponseDto updateUser(@PathVariable("id") UUID id, @RequestBody UserUpdateDto userUpdateDto) {
       return userService.update(id,userUpdateDto);

    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") UUID id){
        userService.delete(id);
    }

}
