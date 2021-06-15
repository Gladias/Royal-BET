package com.gladias.royalbet.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity(name = "status")
public class BetStatusEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String status;

    public BetStatusEntity(String status) {
        this.status = status;
    }

    private double win;
}
