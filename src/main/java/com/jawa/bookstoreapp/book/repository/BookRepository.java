package com.jawa.bookstoreapp.book.repository;

import com.jawa.bookstoreapp.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

}
