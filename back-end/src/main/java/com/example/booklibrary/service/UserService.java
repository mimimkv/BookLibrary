package com.example.booklibrary.service;

import com.example.booklibrary.dto.BorrowDto;
import com.example.booklibrary.dto.UserDto;
import com.example.booklibrary.exceptions.BookNotFoundException;
import com.example.booklibrary.exceptions.UserNotFoundException;
import com.example.booklibrary.model.Book;
import com.example.booklibrary.model.Borrow;
import com.example.booklibrary.model.User;
import com.example.booklibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static final String USER_NOT_FOUND_MESSAGE_TEMPLATE = "User with id %d not found";
    private static final String INVALID_EMAIL_MESSAGE_TEMPLATE = "User with email %s not found";

    private final UserRepository userRepository;

    private final BorrowService borrowService;

    private final BookService bookService;

    @Autowired
    public UserService(UserRepository userRepository, BorrowService borrowService, BookService bookService) {
        this.userRepository = userRepository;
        this.borrowService = borrowService;
        this.bookService = bookService;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
            () -> new UserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE_TEMPLATE, id)));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, UserDto userDto) throws UserNotFoundException {
        if (!userExists(id)) {
            throw new UserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE_TEMPLATE, id));
        }

        User user = User.from(userDto);
        user.setId(id);

        return userRepository.save(user);
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        if (!userExists(id)) {
            throw new UserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE_TEMPLATE, id));
        }

        userRepository.deleteById(id);
    }

    @Transactional
    public User borrowBook(Long userId, Long bookIsbn) {
        User user = getUserById(userId);
        Book book = bookService.getBookByIsbn(bookIsbn);

        Borrow borrow = new Borrow();
        borrow.setUser(user);
        borrow.setBook(book);

        Borrow createdBorrow = borrowService.createBorrow(borrow);
        user.borrow(createdBorrow);
        return user;
    }

    public User getUserByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
            () -> new UserNotFoundException(String.format(INVALID_EMAIL_MESSAGE_TEMPLATE, email)));
    }

    private boolean userExists(Long id) {
        return userRepository.findById(id).isPresent();
    }
}
