package com.gladias.royalbet.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime time;

    private String hostTeam;
    private String visitorsTeam;

    private Double liveOdds;

    @OneToMany(mappedBy = "game")
    private Set<Bet> bets;

    public Game(LocalDateTime time, String hostTeam, String visitorsTeam, Double liveOdds) {
        this.time = time;
        this.hostTeam = hostTeam;
        this.visitorsTeam = visitorsTeam;
        this.liveOdds = liveOdds;
    }
}
