package com.gladias.royalbet.repository;

import com.gladias.royalbet.model.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GameRepository extends JpaRepository<GameEntity, Long> {

}
