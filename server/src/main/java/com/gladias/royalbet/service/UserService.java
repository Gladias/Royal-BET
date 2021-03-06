package com.gladias.royalbet.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gladias.royalbet.exception.NoPasswordMatchException;
import com.gladias.royalbet.exception.UserAlreadyExistException;
import com.gladias.royalbet.model.UserEntity;
import com.gladias.royalbet.payload.BalanceRequest;
import com.gladias.royalbet.payload.RegisterRequest;
import com.gladias.royalbet.payload.UserDto;
import com.gladias.royalbet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Base64;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserDto getUserData(String username) {
        UserEntity user = repository.findByLogin(username).get();

        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .email(user.getEmail())
                .money(user.getMoney())
                .build();
    }

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
                .money(50.0)
                .build();

        repository.save(user);
    }

    public BalanceRequest changeBalance(String username, BalanceRequest request) {
        UserEntity user = repository.findByLogin(username).get();
        user.setMoney(user.getMoney() + request.getBalance());
        repository.save(user);
        return new BalanceRequest(user.getMoney());
    }

    public static String getUsernameFromToken(String jwtToken) {
        return JWT.require(Algorithm.HMAC256("testSecret"))
            .build()
            .verify(jwtToken)
            .getSubject();
    }
}
