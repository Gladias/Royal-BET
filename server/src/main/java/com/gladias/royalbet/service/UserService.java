package com.gladias.royalbet.service;

import com.gladias.royalbet.exception.NoPasswordMatchException;
import com.gladias.royalbet.exception.UserAlreadyExistException;
import com.gladias.royalbet.model.UserEntity;
import com.gladias.royalbet.payload.UserDto;
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

    public void registerUserAccount(@NotNull UserDto userDto) throws UserAlreadyExistException,
                                                                     NoPasswordMatchException {
        if (repository.existsByLogin(userDto.getLogin())) {
            throw new UserAlreadyExistException("There is an account with login: " + userDto.getLogin());
        } else if (repository.existsByEmail(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with email: " + userDto.getEmail());
        } else if (!userDto.getPassword().equals(userDto.getPasswordRepeat())) {
            throw new NoPasswordMatchException("Provided passwords do not match");
        }

        UserEntity user = UserEntity.builder()
                .login(userDto.getLogin())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .build();

        repository.save(user);
    }
}
