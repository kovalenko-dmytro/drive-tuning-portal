package com.drivetuningsh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequestDto {

    @NotBlank(message = "message = *Please provide your first name")
    private String firstName;

    @NotBlank(message = "message = *Please provide your last name")
    private String lastName;

    @Email(message = "*Please provide a valid Email")
    private String email;

    @NotBlank(message = "message = *Please provide your password")
    @Size(min = 6, message = "message = *Please more 6 symbol")
    private String password;
}
