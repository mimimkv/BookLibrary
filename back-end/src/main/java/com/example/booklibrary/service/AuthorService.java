package com.example.booklibrary.service;

import com.example.booklibrary.exceptions.AuthorNotFoundException;
import com.example.booklibrary.model.Author;
import com.example.booklibrary.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author findByName(String firstName, String lastName) {
        List<Author> allAuthors = authorRepository.findAll();
        for (Author a : allAuthors) {
            if (a.getFirstName().equalsIgnoreCase(firstName) &&
                a.getLastName().equalsIgnoreCase(lastName)) {
                return a;
            }
        }

        throw new AuthorNotFoundException("This author is not present in the database");
    }
}
