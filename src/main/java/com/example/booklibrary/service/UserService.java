package com.example.booklibrary.service;

import com.example.booklibrary.dto.BorrowDto;
import com.example.booklibrary.dto.UserDto;
import com.example.booklibrary.exceptions.UserNotFoundException;
import com.example.booklibrary.model.Borrow;
import com.example.booklibrary.model.User;
import com.example.booklibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    private static final String USER_NOT_FOUND_MESSAGE_TEMPLATE = "User with id %d not found";

    private final UserRepository userRepository;

    private BorrowService borrowService;

    @Autowired
    public UserService(UserRepository userRepository, BorrowService borrowService) {
        this.userRepository = userRepository;
        this.borrowService = borrowService;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(
            () -> new UserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE_TEMPLATE, id)));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User user) throws UserNotFoundException {
        if (!userExists(id)) {
            throw new UserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE_TEMPLATE, id));
        }

        return userRepository.save(user);
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        if (!userExists(id)) {
            throw new UserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE_TEMPLATE, id));
        }

        userRepository.deleteById(id);
    }

    @Transactional
    public User borrowBook(Long userId, BorrowDto borrowDto) throws UserNotFoundException {
        User user = getUserById(userId);
        Borrow borrow = borrowService.createBorrow(Borrow.from(borrowDto));
        user.borrow(borrow);
        borrow.setUser(user);
        return user;
    }

    private boolean userExists(Long id) {
        return userRepository.findById(id).isPresent();
    }
}
