package com.gladias.royalbet.repository;

import com.gladias.royalbet.model.BetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetRepository extends JpaRepository<BetEntity, Long> {
}
