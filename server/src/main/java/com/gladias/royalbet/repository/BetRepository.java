package com.gladias.royalbet.repository;

import com.gladias.royalbet.model.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetRepository extends JpaRepository<Bet, Long> {
}
