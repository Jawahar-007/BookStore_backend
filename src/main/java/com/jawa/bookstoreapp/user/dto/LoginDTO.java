package com.jawa.bookstoreapp.user.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@ToString
public class LoginDTO {

    private String userEmailId;
    private String userPassword;

    public String getUserEmailId() {
        return userEmailId;
    }

    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
