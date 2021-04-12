package com.gladias.royalbet.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@Entity(name = "bets")
public class BetEntity {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime placedAt;

    private Double stake;
    private Double odds;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private GameEntity game;
}
