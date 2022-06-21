package com.example.booklibrary.repository;

import com.example.booklibrary.model.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    Optional<Borrow> findByUserIdAndBookIsbn(Long userId, Long bookIsbn);
}
