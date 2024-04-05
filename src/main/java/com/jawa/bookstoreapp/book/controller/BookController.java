package com.jawa.bookstoreapp.book.controller;


import com.jawa.bookstoreapp.book.entity.BookEntity;
import com.jawa.bookstoreapp.book.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    IBookService iBookService;

    @GetMapping("/getAllbooks")
    public List<BookEntity> getAllBooks(){
        return iBookService.getAllBooks();
    }

    @PostMapping("/addbooks")
    @ResponseStatus(HttpStatus.CREATED)
    public List<BookEntity> addbooks(@RequestBody BookEntity bookEntity)
    {
        return iBookService.addbooks(bookEntity);
    }

    @PutMapping("editbook/{id}")
    public String editBookById (@PathVariable Long id,BookEntity bookEntity)
    {
        return iBookService.editBookByID(id,bookEntity);
    }

    @DeleteMapping("/delete")
    public String deleteBook(@PathVariable Long id)
    {
        return iBookService.deleteBookByID(id);
    }
    

}
