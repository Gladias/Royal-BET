package com.gladias.royalbet.payload;

import com.gladias.royalbet.model.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BetResponse {
    private final Long id;

    private final LocalDateTime placedAt;

    private final Double stake;
    private final String winner;

    private final GameEntity game;
    private final OddsEntity odds;
    private final BetStatusEntity status;

    public static BetResponse of(BetEntity entity) {
        return new BetResponse(entity.getId(),
                entity.getPlacedAt(),
                entity.getStake(),
                entity.getWinner(),
                entity.getGame(),
                entity.getGame().getOdds(),
                entity.getStatus());
    }
}
