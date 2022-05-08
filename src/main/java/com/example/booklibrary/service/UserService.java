package com.example.booklibrary.service;

import com.example.booklibrary.dto.UserDto;
import com.example.booklibrary.exceptions.UserNotFoundException;
import com.example.booklibrary.model.User;
import com.example.booklibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private static final String USER_NOT_FOUND_MESSAGE_TEMPLATE = "User with id %d not found";

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(
            () -> new UserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE_TEMPLATE, id)));
    }

    public User createUser(UserDto userDto) {
        return userRepository.save(UserDto.mapToUser(userDto));
    }

    public User updateUser(Long id, UserDto userDto) throws UserNotFoundException {
        if (!userExists(id)) {
            throw new UserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE_TEMPLATE, id));
        }

        return userRepository.save(UserDto.mapToUser(userDto));
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        if (!userExists(id)) {
            throw new UserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE_TEMPLATE, id));
        }

        userRepository.deleteById(id);
    }

    private boolean userExists(Long id) {
        return userRepository.findById(id).isPresent();
    }
}
