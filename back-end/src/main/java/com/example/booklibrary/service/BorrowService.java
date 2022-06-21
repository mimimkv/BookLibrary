package com.example.booklibrary.service;

import com.example.booklibrary.exceptions.BookNotFoundException;
import com.example.booklibrary.exceptions.BorrowNotFoundException;
import com.example.booklibrary.model.Borrow;
import com.example.booklibrary.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowService {
    private static final int BORROW_DAYS = 20;

    private BorrowRepository borrowRepository;

    @Autowired
    public BorrowService(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    public Borrow createBorrow(Borrow borrow) {
        borrow.setReturnDate(LocalDate.now().plusDays(BORROW_DAYS));
        return borrowRepository.save(borrow);
    }

    public Page<Borrow> getAllBorrows(Pageable page) {
        return borrowRepository.findAll(page);
    }

    public Borrow getBorrowById(Long id) throws BorrowNotFoundException {
        return borrowRepository.findById(id)
                .orElseThrow(() -> new BorrowNotFoundException("This borrow cannot be found."));
    }

    public Borrow getBorrow(Long userId, Long bookIsbn) {
        return borrowRepository.findByUserIdAndBookIsbn(userId, bookIsbn)
            .orElseThrow(() -> new BorrowNotFoundException("This borrow cannot be found."));
    }

    public void deleteBorrow(Long id) throws BorrowNotFoundException {
        if (!borrowExists(id)) {
            throw new BookNotFoundException("This borrow cannot be found.");
        }

        borrowRepository.deleteById(id);
    }

    public Borrow updateBorrow(Long id, Borrow borrow) throws BorrowNotFoundException {
        Borrow borrowToUpdate = getBorrowById(id);
        borrowToUpdate.setUser(borrow.getUser());

        return borrowRepository.save(borrow);
    }

    private boolean borrowExists(Long id) {
        return borrowRepository.findById(id).isPresent();
    }
}
