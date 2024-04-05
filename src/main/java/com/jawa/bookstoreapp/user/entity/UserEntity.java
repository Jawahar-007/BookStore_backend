package com.jawa.bookstoreapp.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

//id - Long ✔️
//First name - String ✔️
//Last name - String ✔️
//Dob - LocalDate ✔️+
//Registered date - LocalDate ✔️   - localDate.now()
//updated date - LocalDate ✔️
//Password - String ✔️ (passwordencoder) -> BcrpytPasswordEncoder
//Email ID - String ✔️
//Boolean Verify ✔️
//Otp-6 digit



@ToString
@Getter
@Setter
@Entity
@Table(name = "user_tbl")
public class UserEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "Id")
   private Long id;
   @Column(name = "FirstName")
   private String firstname;
   @Column(name = "LastName")
   private String lastname;
   @Column(name = "Dob")
   private LocalDate dob;
   @Column(name = "Register_Date")
   private LocalDate registerDate;
   @Column(name = "updateDate")
   private LocalDate updateDate;
   @Getter
   @Column(name = "Password")
   private String Password;
   @Column(name = "Email")
   private String email;
   @Column(name = "Otp")
   private String otp;


   public UserEntity(Long id, String firstname, String lastname, LocalDate dob, LocalDate registerDate, LocalDate updateDate, String Password, String email) {
      this.id = id;
      this.firstname = firstname;
      this.lastname = lastname;
      this.dob = dob;
      this.registerDate = registerDate;
      this.updateDate = updateDate;
      this.Password = Password;
      this.email = email;
   }
   public UserEntity(){

   }

   }

