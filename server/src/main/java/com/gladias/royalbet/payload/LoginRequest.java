package com.gladias.royalbet.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

public class LoginRequest {
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
