package com.gladias.royalbet.payload;

import com.gladias.royalbet.model.GameEntity;
import com.gladias.royalbet.model.OddsEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GameResponse {
    private final Long id;

    private final LocalDateTime time;

    private final String hostTeam;
    private final String visitorsTeam;

    private final OddsEntity odds;

    public static GameResponse of(GameEntity entity) {
        return new GameResponse(entity.getId(),
                entity.getTime(),
                entity.getHostTeam(),
                entity.getVisitorsTeam(),
                entity.getOdds());
    }
}
