package com.gladias.royalbet.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Bet {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime placedAt;

    private Double stake;
    private Double odds;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
}
