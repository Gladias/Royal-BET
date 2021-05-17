package com.gladias.royalbet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Set;


@Data
@NoArgsConstructor
@Entity(name = "games")
public class GameEntity {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime time;

    private String hostTeam;
    private String visitorsTeam;

    private Double liveOdds;

    @OneToMany(mappedBy = "game")
    @JsonIgnore
    private Set<BetEntity> bets;
}
