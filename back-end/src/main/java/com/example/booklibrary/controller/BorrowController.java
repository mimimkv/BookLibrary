package com.example.booklibrary.controller;

import com.example.booklibrary.dto.BorrowDto;
import com.example.booklibrary.model.Borrow;
import com.example.booklibrary.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("library/borrows")
@CrossOrigin()
public class BorrowController {

    private final BorrowService borrowService;

    @Autowired
    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @GetMapping("")
    public ResponseEntity<Page<BorrowDto>> getAllBorrows(Pageable page) {
        Page<Borrow> borrows = borrowService.getAllBorrows(page);
        Page<BorrowDto> borrowsDtoList =borrows.map(BorrowDto::from);

        return new ResponseEntity<>(borrowsDtoList, HttpStatus.OK);
    }

    @GetMapping("/userId/{userId}/bookIsbn/{bookIsbn}")
    public ResponseEntity<BorrowDto> getBorrow(@PathVariable Long userId, @PathVariable Long bookIsbn) {
        Borrow borrow = borrowService.getBorrow(userId, bookIsbn);
        return new ResponseEntity<>(BorrowDto.from(borrow), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<BorrowDto> createBorrow(@Valid @RequestBody BorrowDto borrowDto) {
        Borrow borrow = borrowService.createBorrow(Borrow.from(borrowDto));
        return new ResponseEntity<>(BorrowDto.from(borrow), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteBorrow(@PathVariable Long id) {
        borrowService.deleteBorrow(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
