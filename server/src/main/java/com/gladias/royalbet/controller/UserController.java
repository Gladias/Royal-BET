package com.gladias.royalbet.controller;


import com.gladias.royalbet.exception.NoPasswordMatchException;
import com.gladias.royalbet.exception.UserAlreadyExistException;
import com.gladias.royalbet.payload.RegisterRequest;
import com.gladias.royalbet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService service;

    @PostMapping("/register")
    public void registerUserAccount(@RequestBody @Valid RegisterRequest registerRequest) {
        try {
            service.registerUserAccount(registerRequest);
        } catch (UserAlreadyExistException | NoPasswordMatchException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
