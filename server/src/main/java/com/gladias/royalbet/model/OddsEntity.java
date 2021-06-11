package com.gladias.royalbet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Data
@NoArgsConstructor
@Entity(name = "odds")
public class OddsEntity {

    @Id
    @GeneratedValue
    private Long id;

    double hostWinOdds;
    double tieOdds;
    double visitorsWinOdds;

    public OddsEntity(double hostWinOdds, double tieOdds, double visitorsWinOdds) {
        this.hostWinOdds = hostWinOdds;
        this.tieOdds = tieOdds;
        this.visitorsWinOdds = visitorsWinOdds;
    }

    @OneToOne(mappedBy = "odds")
    @JsonIgnore
    private GameEntity game;
}
