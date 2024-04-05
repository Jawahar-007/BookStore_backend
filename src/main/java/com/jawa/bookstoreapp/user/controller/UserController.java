package com.jawa.bookstoreapp.user.controller;


import com.jawa.bookstoreapp.user.dto.ForgotPasswordDTO;
import com.jawa.bookstoreapp.user.dto.LoginDTO;
import com.jawa.bookstoreapp.user.dto.ResetPasswordDTO;
import com.jawa.bookstoreapp.user.entity.UserEntity;
import com.jawa.bookstoreapp.user.service.IUserService;
import com.jawa.bookstoreapp.user.util.EmailSender;
import com.jawa.bookstoreapp.user.util.UserJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseStatus(HttpStatus.ACCEPTED)
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    UserJWT jwt;
    @Autowired
    EmailSender emailSender;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String userRegister(@RequestBody UserEntity userEntity){
            return userService.userRegister(userEntity);
    }

    @PostMapping("/login")
    public String userLogin(@RequestBody LoginDTO loginDTO) {
        return userService.userLogin(loginDTO);
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<String> forgetPassword(@RequestBody ForgotPasswordDTO forgetPasswordDTO) {
        String email = forgetPasswordDTO.getEmail();
        userService.sendOTPToEmail(email);
        return ResponseEntity.ok("OTP sent to email for password reset.");
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
        String email =resetPasswordDTO.getEmail();
        String otp =resetPasswordDTO.getOtp();
        String newPassword = resetPasswordDTO.getNewPassword();
        userService.resetPassword(email,otp,newPassword);
        return ResponseEntity.ok("Password reset successfully.");
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<UserEntity> deleteUser(@PathVariable long id)
    {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
