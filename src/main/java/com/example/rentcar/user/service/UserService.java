package com.example.rentcar.user.service;

import com.example.rentcar.user.dto.UserResponseDto;
import com.example.rentcar.user.dto.UserUpdateDto;
import com.example.rentcar.user.entity.User;
import com.example.rentcar.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ModelMapper mapper = new ModelMapper();
    private final UserRepository userRepository;


    public UserResponseDto create(UserResponseDto responseDto) {
        User user = mapper.map(responseDto, User.class);
        user = userRepository.save(user);
        return mapper.map(user, UserResponseDto.class);
    }

    public List<UserResponseDto> getUsers() {
        List<User> all = userRepository.findAll();
        return all.stream()
                .map(user -> mapper.map(user, UserResponseDto.class))
                .toList();
    }

    public UserResponseDto getUser(UUID id) {
        User user = userRepository.findById(id).get();
        return mapper.map(user, UserResponseDto.class);
    }

    public void update(UUID id, UserUpdateDto userUpdateDto) {

    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}
