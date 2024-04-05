package com.jawa.bookstoreapp.book.service;

import com.jawa.bookstoreapp.book.entity.BookEntity;
import com.jawa.bookstoreapp.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService implements IBookService{
    @Autowired
    private BookRepository bookrepo;


    @Override
    public List<BookEntity> getAllBooks() {
        return bookrepo.findAll();
    }
    @Override
    public List<BookEntity> addbooks(BookEntity bookEntity){
         bookrepo.save(bookEntity);
         return getAllBooks();
    }
    @Override
    public BookEntity getBookById(Long id) {
        return bookrepo.findById(id).orElseThrow(() -> new IllegalArgumentException("bookID bot found"));
    }


    @Override
    public String editBookByID(Long id ,BookEntity bookEntity ) {

            BookEntity user = bookrepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Book id not found"));
            user.setBookAuthor(bookEntity.getBookAuthor());
            user.setBookDescription(bookEntity.getBookDescription());
            user.setBookQuantity(bookEntity.getBookQuantity());
            user.setBookPrice(bookEntity.getBookPrice());
            user.setBookName(bookEntity.getBookName());
            bookrepo.save(user);
            return  " Book Details Updated" ;

    }

    @Override
    public String deleteBookByID(Long id) {
        if(bookrepo.existsById(id))
        {
            bookrepo.deleteById(id);
            return "Book Deleted .";
        }

        else
            return "Book Not Found with :" + id;
    }


}
