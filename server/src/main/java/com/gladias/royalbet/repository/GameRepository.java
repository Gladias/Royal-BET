package com.gladias.royalbet.repository;

import com.gladias.royalbet.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
