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

    @NotBlank(message = "{valid.first.name}")
    private String firstName;

    @NotBlank(message = "{valid.last.name}")
    private String lastName;

    @Email(message = "{valid.email}")
    private String email;

    @NotBlank(message = "{valid.password}")
    @Size(min = 6, message = "{valid.password.size}")
    private String password;
}
