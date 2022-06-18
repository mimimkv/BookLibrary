package com.example.booklibrary.service;

import com.example.booklibrary.exceptions.BorrowNotFoundException;
import com.example.booklibrary.model.Borrow;
import com.example.booklibrary.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowService {
    private BorrowRepository borrowRepository;

    @Autowired
    public BorrowService(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    public Borrow createBorrow(Borrow borrow) {
        return borrowRepository.save(borrow);
    }

    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    public Borrow getBorrowById(Long id) throws BorrowNotFoundException {
        return borrowRepository.findById(id)
                .orElseThrow(() -> new BorrowNotFoundException("This borrow cannot be found."));
    }

    public Borrow deleteBorrow(Long id) throws BorrowNotFoundException {
        Borrow borrow = getBorrowById(id);
        borrowRepository.delete(borrow);
        return borrow;
    }

    public Borrow updateBorrow(Long id, Borrow borrow) throws BorrowNotFoundException {
        Borrow borrowToUpdate = getBorrowById(id);
        borrowToUpdate.setUser(borrow.getUser());

        return borrowRepository.save(borrow);
    }
}
