package com.gladias.royalbet.service;

import com.gladias.royalbet.model.BetEntity;
import com.gladias.royalbet.model.GameEntity;
import com.gladias.royalbet.repository.*;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneralService {
    @Value("${rapidapi.key}")
    private String key;

    @Value("${rapidapi.host}")
    private String host;

    private final GameRepository gameRepository;
    private final OddsRepository oddsRepository;
    private final BetRepository betRepository;
    private final UserRepository userRepository;
    private final BetStatusRepository betStatusRepository;

    public void checkBets() throws Exception {
        LocalDateTime day = LocalDateTime.now();
        day = day.minusDays(1);
        DateTimeFormatter requestFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String requestDate = day.format(requestFormatter);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api-nba-v1.p.rapidapi.com/games/date/" + requestDate))
                .header("x-rapidapi-key", key)
                .header("x-rapidapi-host", host)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        String body;
        JSONObject json = new JSONObject(response.body());

        JSONArray array = json.getJSONObject("api").getJSONArray("games");

        for (int j = 0; j < array.length(); j++) {
            var game = array.getJSONObject(j);

            String hostTeam = game.getJSONObject("hTeam").getString("fullName");
            String visitorsTeam = game.getJSONObject("vTeam").getString("fullName");

            int hostTeamResult = Integer.parseInt(game.getJSONObject("hTeam").getJSONObject("score").getString("points"));
            int visitorsTeamResult = Integer.parseInt(game.getJSONObject("vTeam").getJSONObject("score").getString("points"));

            String winner;

            if (hostTeamResult > visitorsTeamResult) {
                winner = hostTeam;
            } else if (hostTeamResult < visitorsTeamResult) {
                winner = visitorsTeam;
            } else {
                winner = "Tie";
            }

            GameEntity gameEntity = gameRepository.findFirstByHostTeamAndVisitorsTeam(hostTeam, visitorsTeam).get();
            List<BetEntity> betEntities = betRepository.findAllByGame_id(gameEntity.getId());

            for (BetEntity betEntity : betEntities) {
                if (betEntity.getStatus().equals("Expired"))
                    continue;

                String selectedWinner = betEntity.getWinner();
                Double stake = betEntity.getStake();
                double odds;

                if (winner.equals(hostTeam)) {
                    odds = gameEntity.getOdds().getHostWinOdds();
                } else if (winner.equals(visitorsTeam)) {
                    odds = gameEntity.getOdds().getVisitorsWinOdds();
                } else {
                    odds = gameEntity.getOdds().getTieOdds();
                }

                betEntity.getStatus().setStatus("Expired");
                if (selectedWinner.equals(winner)) {
                    betEntity.getStatus().setWin(Math.round(stake * odds * 100.0) / 100.0);
                    betEntity.getUser().setMoney(betEntity.getUser().getMoney() + Math.round(stake * odds * 100.0) / 100.0);
                    userRepository.save(betEntity.getUser());
                    betStatusRepository.save(betEntity.getStatus());
                }
            }
        }
    }
}
