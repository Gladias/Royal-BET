package com.gladias.royalbet.model;

import lombok.Builder;
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
    private String winner;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private GameEntity game;

    @OneToOne
    @JoinColumn(name = "status_id")
    private BetStatusEntity status;

    @Builder
    public BetEntity(LocalDateTime placedAt, Double stake, String winner,
                     UserEntity user, GameEntity game, BetStatusEntity status) {
        this.placedAt = placedAt;
        this.stake = stake;
        this.winner = winner;
        this.user = user;
        this.game = game;
        this.status = status;
    }
}
