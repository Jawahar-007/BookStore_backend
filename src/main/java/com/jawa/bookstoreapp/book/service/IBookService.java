package com.jawa.bookstoreapp.book.service;

import com.jawa.bookstoreapp.book.entity.BookEntity;
import java.util.List;

public interface IBookService {

    List<BookEntity> getAllBooks();
    List<BookEntity> addbooks(BookEntity bookEntity);
    BookEntity getBookById(Long id);

    String editBookByID(Long id, BookEntity bookEntity);

    String deleteBookByID(Long id);
}
