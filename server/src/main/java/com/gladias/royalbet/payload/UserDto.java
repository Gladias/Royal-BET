package com.gladias.royalbet.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserDto {

    @NotBlank
    @Size(min = 2, max = 20)
    private String login;

    @NotBlank
    private String password;

    @NotBlank
    private String passwordRepeat;

    @Email
    private String email;
}
