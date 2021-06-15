package com.gladias.royalbet.payload;

import lombok.Data;

@Data
public class SingleBet {
    private Double stake;
    private String winner;
    private Long gameId;
}
