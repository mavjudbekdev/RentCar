package com.example.rentcar.user.service;

import com.example.rentcar.user.dto.UserResponseDto;
import com.example.rentcar.user.dto.UserUpdateDto;
import com.example.rentcar.user.entity.User;
import com.example.rentcar.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

        Optional<User> byId = userRepository.findById(id);
        if(byId.isEmpty()){
            System.out.println("User not found");
            return null;
        }

        return mapper.map(byId.get(), UserResponseDto.class);
    }

    public UserResponseDto update(UUID id, UserUpdateDto userUpdateDto) {

        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) {
            System.out.println("User not found");
            return null;
        }

        User user = byId.get();
        user.setName(userUpdateDto.getName());
        user.setSurname(userUpdateDto.getSurname());
        user.setPhoneNumber(userUpdateDto.getPhoneNumber());
        user.setPassportNumber(userUpdateDto.getPassportNumber());
        userRepository.save(user);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setSurname(user.getSurname());
        userResponseDto.setPassportNumber(user.getPassportNumber());
        userResponseDto.setPhoneNumber(user.getPhoneNumber());

        return userResponseDto;
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}
