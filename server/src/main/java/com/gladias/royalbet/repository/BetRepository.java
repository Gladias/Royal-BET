package com.gladias.royalbet.repository;

import com.gladias.royalbet.model.BetEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BetRepository extends JpaRepository<BetEntity, Long> {
    List<BetEntity> findAllByUser_id(Long userId, Pageable page);
    List<BetEntity> findAllByUserLogin(String login, Pageable page);

}
