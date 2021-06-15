package com.gladias.royalbet.repository;

import com.gladias.royalbet.model.BetEntity;
import com.gladias.royalbet.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByLogin(String login);
    boolean existsByLogin(String login);
    boolean existsByEmail(String email);
    BetEntity findByBets_id(Long betId);
}
