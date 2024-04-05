package com.jawa.bookstoreapp.book.entity;

import jakarta.persistence.*;
import lombok.*;



@Getter
@Setter
@ToString

@Entity
@Table(name = "Book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BookId")
    private Long bookId;
    @Column(name = "BookName")
    private String bookName;
    @Column(name = "BookAuthor")
    private String bookAuthor;
    @Column(name = "BookDescription")
    private String bookDescription;
    @Column(name = "BookLogo")
    private String bookLogo;
    @Column(name = "BookPrice")
    private int bookPrice;
    @Column(name = "BookQuantity")
    private int bookQuantity;


}


//Id
//Book Name
//Book Author
//Bookdescription
//Book Logo-MultiPart
//Book Price
//Book Quantity—>20-4=16
