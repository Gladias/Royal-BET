package com.gladias.royalbet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
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

    @Builder
    public GameEntity(LocalDateTime time, String hostTeam, String visitorsTeam, OddsEntity odds) {
        this.time = time;
        this.hostTeam = hostTeam;
        this.visitorsTeam = visitorsTeam;
        this.odds = odds;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "game")
    private Set<BetEntity> bets;

    @OneToOne
    @JoinColumn(name = "odds_id")
    private OddsEntity odds;
}
