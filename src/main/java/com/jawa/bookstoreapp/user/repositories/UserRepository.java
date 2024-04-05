package com.jawa.bookstoreapp.user.repositories;

import com.jawa.bookstoreapp.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    @Query("SELECT v from UserEntity v  WHERE v.email = :Email")
    UserEntity findByEmailID(@Param("Email") String email);
    @Query("SELECT v from UserEntity v WHERE v.firstname = :firstname ")
    UserEntity findByFirstName(@Param("firstname") String firstname);

}

//@Query("SELECT v from UserEntity v WHERE v.email = :Email AND v.Password = :userPassword")
//UserEntity findByEmailAndPassword(@Param("Email") String email, @Param("userPassword") String userPassword);