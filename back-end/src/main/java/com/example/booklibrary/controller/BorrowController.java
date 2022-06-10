package com.example.booklibrary.controller;

import com.example.booklibrary.dto.BorrowDto;
import com.example.booklibrary.model.Borrow;
import com.example.booklibrary.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("library/borrows")
@CrossOrigin
public class BorrowController {

    private final BorrowService borrowService;

    @Autowired
    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @GetMapping("")
    public ResponseEntity<List<BorrowDto>> getAllBorrows() {
        List<Borrow> borrows = borrowService.getAllBorrows();
        List<BorrowDto> borrowsDtoList = borrows.stream()
            .map(BorrowDto::from)
            .collect(Collectors.toList());

        return new ResponseEntity<>(borrowsDtoList, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<BorrowDto> createBorrow(@Valid @RequestBody BorrowDto borrowDto) {
        Borrow borrow = borrowService.createBorrow(Borrow.from(borrowDto));
        return new ResponseEntity<>(BorrowDto.from(borrow), HttpStatus.OK);
    }
}
