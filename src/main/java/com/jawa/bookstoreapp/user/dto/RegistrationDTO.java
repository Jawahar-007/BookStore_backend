package com.jawa.bookstoreapp.user.dto;

import lombok.*;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter

public class RegistrationDTO {
//    @NotNull(message = "First name cannot be empty")
//  /  @Pattern(regexp = "^[A-Z][a-z]+$", message = "Invalid first name format")
    private String firstname;
//    @NotBlank(message = "Last name cannot be empty")
//    @Pattern(regexp = "[A-Z][a-z]+$", message = "Invalid lastname format")
    private String lastname;
//    @NotBlank(message = "DOB cannot be empty")
//    @FutureOrPresent(message = "Enter a valid Date Of Birth")
//    private LocalDate dob;
//    @Email
//    @NotBlank(message = "Email cannot be empty")
//    @Pattern(regexp = "^[a-z0-9.+\\-_]+[@][a-z]{3,}[.][a-z]{2,}$",message = "Invalid email format")
    private String email;
//    @NotEmpty
    private String password;

    public RegistrationDTO(String firstname, String lastname, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }
}

