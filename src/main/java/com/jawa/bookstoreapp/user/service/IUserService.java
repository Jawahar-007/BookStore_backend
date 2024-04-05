package com.jawa.bookstoreapp.user.service;

import com.jawa.bookstoreapp.user.dto.LoginDTO;
import com.jawa.bookstoreapp.user.dto.ResetPasswordDTO;
import com.jawa.bookstoreapp.user.entity.UserEntity;


public interface IUserService {

    String userRegister(UserEntity userEntity);

    String userLogin(LoginDTO loginDTO);

    void sendOTPToEmail(String email);

    void deleteUser(Long id);


    void resetPassword(String email , String otp , String newPassword);
//    Optional<UserEntity> getUserByJWT(String token);

}

//    List<UserRegistrationDTO> getallUser();
//    List<UserRegistrationDTO> fetchUserById(@PathVariable int id);
//    void createUser(@RequestBody UserEntity details);
//    UserEntity updateUser(@PathVariable int id);
//    void deleteUser(@PathVariable int id);