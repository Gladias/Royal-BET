package com.gladias.royalbet.payload;

import lombok.Data;

import java.util.List;

@Data
public class BetsRequest {
    List<SingleBet> bets;
}
