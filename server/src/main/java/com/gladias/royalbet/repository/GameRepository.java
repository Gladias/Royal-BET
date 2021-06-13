package com.gladias.royalbet.repository;

import com.gladias.royalbet.model.GameEntity;
import com.gladias.royalbet.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface GameRepository extends JpaRepository<GameEntity, Long> {

}
