package com.gladias.royalbet.service;

import com.gladias.royalbet.exception.NoPasswordMatchException;
import com.gladias.royalbet.exception.UserAlreadyExistException;
import com.gladias.royalbet.model.UserEntity;
import com.gladias.royalbet.payload.RegisterRequest;
import com.gladias.royalbet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void registerUserAccount(@NotNull RegisterRequest registerRequest) throws UserAlreadyExistException,
                                                                     NoPasswordMatchException {
        if (repository.existsByLogin(registerRequest.getLogin())) {
            throw new UserAlreadyExistException("There is an account with login: " + registerRequest.getLogin());
        } else if (repository.existsByEmail(registerRequest.getEmail())) {
            throw new UserAlreadyExistException("There is an account with email: " + registerRequest.getEmail());
        } else if (!registerRequest.getPassword().equals(registerRequest.getPasswordConfirm())) {
            throw new NoPasswordMatchException("Provided passwords do not match");
        }

        UserEntity user = UserEntity.builder()
                .login(registerRequest.getLogin())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .build();

        repository.save(user);
    }
}
