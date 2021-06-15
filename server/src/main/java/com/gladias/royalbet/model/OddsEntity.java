package com.gladias.royalbet.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


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
}
