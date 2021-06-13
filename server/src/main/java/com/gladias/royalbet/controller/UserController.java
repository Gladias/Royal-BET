package com.gladias.royalbet.controller;


import com.gladias.royalbet.exception.NoPasswordMatchException;
import com.gladias.royalbet.exception.UserAlreadyExistException;
import com.gladias.royalbet.payload.BalanceRequest;
import com.gladias.royalbet.payload.RegisterRequest;
import com.gladias.royalbet.payload.UserDto;
import com.gladias.royalbet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService service;

    @GetMapping("/auth/userData")
    public UserDto getUserData(@CookieValue("token") String token) {
        return service.getUserData(UserService.getUsernameFromToken(token));
    }

    @PostMapping("/auth/register")
    public void registerUserAccount(@RequestBody @Valid RegisterRequest registerRequest) {
        try {
            service.registerUserAccount(registerRequest);
        } catch (UserAlreadyExistException | NoPasswordMatchException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/balance")
    public void increaseBalance(@CookieValue("token") String token, @RequestBody BalanceRequest request) {
        service.increaseBalance(UserService.getUsernameFromToken(token), request);
    }
}
